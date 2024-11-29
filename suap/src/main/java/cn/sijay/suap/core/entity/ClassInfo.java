package cn.sijay.suap.core.entity;

import cn.sijay.suap.core.annotation.Translate;
import cn.sijay.suap.core.enums.TranslateType;
import cn.sijay.suap.core.utils.ReflectUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <strong>ClassInfo</strong>
 * <p>
 * 类信息
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Slf4j
@Setter
@Getter
public class ClassInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Class<?> clazz;

    private Map<TranslateType, List<Field>> transFieldMap = new HashMap<>();

    public ClassInfo() {
        super();
    }

    public ClassInfo(Class<?> clazz) {
        super();
        this.clazz = clazz;
        getClazzFieldMap();
    }

    /**
     * 获取需要翻译的字段
     *
     * @param type 翻译类型
     * @return 字段集合
     */
    public List<Field> getTransField(TranslateType type) {
        return new ArrayList<>(transFieldMap.get(type));
    }

    private void getClazzFieldMap() {
        List<Field> declaredFields = ReflectUtil.getAllField(ReflectUtil.newInstance(clazz))
                                                .stream()
                                                .filter(field -> {
                                                    int mod = field.getModifiers();
                                                    Translate translate = field.getAnnotation(Translate.class);
                                                    return !Modifier.isStatic(mod) && !Modifier.isFinal(mod) && !Modifier.isVolatile(mod) && !Modifier.isTransient(mod) && translate != null;
                                                }).toList();
        this.transFieldMap = declaredFields.stream()
                                           .collect(Collectors.toMap(
                                               field -> field.getAnnotation(Translate.class).type(),
                                               Arrays::asList,
                                               (oldList, newList) -> {
                                                   oldList.addAll(newList);
                                                   return oldList;
                                               }
                                           ));
    }

}
