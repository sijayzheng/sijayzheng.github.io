package cn.sijay.suap.core.advice;

import cn.sijay.suap.core.annotation.TranslateIgnore;
import cn.sijay.suap.core.base.BaseException;
import cn.sijay.suap.core.base.ITrans;
import cn.sijay.suap.core.constant.ExceptionConstant;
import cn.sijay.suap.core.handler.TranslateHandler;
import cn.sijay.suap.core.utils.GenerateUtil;
import cn.sijay.suap.core.utils.ReflectUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * <strong>TranslateAdvice</strong>
 * <p>
 * 拦截返回数据进行翻译
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Slf4j
@ControllerAdvice
public class TranslateAdvice<T> implements ResponseBodyAdvice<T> {

    private final Map<Class<?>, Class<?>> proxyClassMap = new HashMap<>();
    private final Map<Class<?>, Set<String>> proxyClassFieldMap = new HashMap<>();
    private final Set<String> notTransClassNames = new HashSet<>();
    private final Map<Object, Object> hasTranslated = new HashMap<>();

    private final TranslateHandler translateHandler;

    public TranslateAdvice(TranslateHandler translateHandler) {
        this.translateHandler = translateHandler;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class clazz) {
        return true;
    }

    @Override
    public T beforeBodyWrite(@Nullable T body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> clazz, ServerHttpRequest request, ServerHttpResponse response) {
        // 如果主动指定了忽略某个方法，则不执行翻译
        if (methodParameter.getExecutable()
                           .isAnnotationPresent(TranslateIgnore.class)) {
            return body;
        }
        try {
            proxyClassMap.clear();
            proxyClassFieldMap.clear();
            notTransClassNames.clear();
            hasTranslated.clear();
            Object o = translateObject(body);
            return o == null ? null : (T) o;
        } catch (Exception e) {
            log.error("翻译错误", e);
        }
        return body;
    }

    /**
     * 翻译单个对象
     */
    @Nullable
    private Object translateObject(@Nullable Object object) throws IllegalAccessException {
        if (object == null) {
            return null;
        }
        if (hasTranslated.containsKey(object)) {
            return hasTranslated.get(object);
        }
        boolean isITrans = false;
        //如果要添加map翻译支持
        if (object instanceof ITrans) {
            translateHandler.transOne((ITrans) object);
            transField(object);
            isITrans = true;
        } else if (object instanceof List<?> collection) {
            List<Object> list = new ArrayList<>();
            for (Object obj : collection) {
                list.add(translateObject(obj));
            }
            return list;
        } else if (object instanceof Set<?> collection) {
            Set<Object> set = new HashSet<>();
            for (Object obj : collection) {
                set.add(translateObject(obj));
            }
            return set;
        } else if (object.getClass()
                         .getName()
                         .startsWith("java.")) {
            return object;
        } else {
            transField(object);
        }
        if (isITrans) {
            ITrans iTrans = (ITrans) object;
            if (iTrans.getTransMap() == null) {
                return iTrans;
            }
            try {
                Class<?> clazz = proxyClassMap.get(iTrans.getClass());
                Set<String> fieldSet = proxyClassFieldMap.get(clazz);
                boolean isGenNewClass = clazz == null || !(fieldSet != null && fieldSet.containsAll(iTrans.getTransMap()
                                                                                                          .keySet()));
                // 如果之前生成过class 并且不缺少字段则不重新生成class
                if (isGenNewClass) {
                    AnnotationDescription jacksonIgnore = AnnotationDescription.Builder.ofType(JsonIgnore.class)
                                                                                       .build();
                    DynamicType.Builder<?> builder = new ByteBuddy()
                        .subclass(iTrans.getClass())
                        .name(iTrans.getClass()
                                    .getSimpleName() + "DynamicTypeBuilder" + GenerateUtil.generateSimpleUUID())
                        .defineMethod("getTransMap", Map.class, Modifier.PUBLIC)
                        .intercept(FixedValue.nullValue())
                        .annotateMethod(jacksonIgnore);
                    for (String property : iTrans.getTransMap()
                                                 .keySet()) {
                        //添加属性
                        builder = builder.defineField(property, String.class, Modifier.PUBLIC);
                    }
                    try (DynamicType.Unloaded<?> make = builder.make()) {
                        clazz = make
                            .load(ClassUtils.getDefaultClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                            .getLoaded();
                    }
                    proxyClassMap.put(iTrans.getClass(), clazz);
                    proxyClassFieldMap.put(clazz, iTrans.getTransMap()
                                                        .keySet());
                }

                Object instance = clazz.getDeclaredConstructor()
                                       .newInstance();
                BeanUtils.copyProperties(iTrans, instance);
                for (String property : iTrans.getTransMap()
                                             .keySet()) {
                    ReflectUtil.setValue(instance, property, iTrans.getTransMap()
                                                                   .get(property));
                }
                hasTranslated.put(object, instance);
                return instance;
            } catch (Exception e) {
                log.error("easy trans 赋值错误", e);
                throw new BaseException(ExceptionConstant.REFLECT_ERROR);
            }
        } else {
            return object;
        }
    }

    /**
     * 翻译一个object的属性
     */
    private void transField(Object object) throws IllegalAccessException {
        if (object == null) {
            return;
        }
        Object tempObj;
        if (object instanceof Map) {
            return;
        }
        //如果是不需要翻译的类，则不翻译了
        if (notTransClassNames.contains(object.getClass()
                                              .getName())) {
            return;
        }
        List<Field> fields = ReflectUtil.getAllField(object);
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod) || Modifier.isVolatile(mod) ||
                Modifier.isTransient(mod) || field.isEnumConstant() || field.getType()
                                                                            .isEnum()) {
                continue;
            }

            field.setAccessible(true);
            tempObj = field.get(object);
            try {
                field.set(object, translateObject(tempObj));
            } catch (Exception e) {
                if (e.getClass()
                     .getName()
                     .contains("java.lang.reflect")) {
                    log.error("检测到反射错误{}，此类后面程序不在走翻译逻辑:{}", e.getClass()
                                                                                 .getName(), object.getClass()
                                                                                                   .getName());
                    notTransClassNames.add(object.getClass()
                                                 .getName());
                    return;
                }
            }
        }
    }

}
