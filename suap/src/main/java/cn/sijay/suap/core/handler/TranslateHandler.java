package cn.sijay.suap.core.handler;

import cn.sijay.suap.core.annotation.Translate;
import cn.sijay.suap.core.base.BaseException;
import cn.sijay.suap.core.base.ITrans;
import cn.sijay.suap.core.constant.ExceptionConstant;
import cn.sijay.suap.core.entity.ClassInfo;
import cn.sijay.suap.core.enums.TranslateType;
import cn.sijay.suap.core.utils.RedisUtil;
import cn.sijay.suap.core.utils.ReflectUtil;
import cn.sijay.suap.core.utils.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.sijay.suap.core.constant.Constants.REDIS_PREFIX_TRANS;

/**
 * <strong>TransService</strong>
 * <p>
 * 翻译服务
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
@Component
public class TranslateHandler {

    private static final Map<String, ClassInfo> CACHE = new HashMap<>();
    private final EntityManager entityManager;

    public TranslateHandler(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public static ClassInfo getClassInfoByName(Class<?> clazz) {
        ClassInfo temp = CACHE.get(clazz.getName());
        ClassInfo info = null;
        if (null == temp) {
            temp = new ClassInfo(clazz);
            CACHE.put(clazz.getName(), null);
        }
        try {
            info = new ClassInfo();
            BeanUtils.copyProperties(temp, info);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return info;
    }

    /**
     * 翻译一个字段
     *
     * @param obj 需要翻译的对象
     */
    public void transOne(ITrans obj) {
        if (obj == null) {
            return;
        }
        ClassInfo info = getClassInfoByName(obj.getClass());
        for (TranslateType translateType : info.getTransFieldMap().keySet()) {
            if (TranslateType.DICTIONARY.equals(translateType)) {
                for (Field field : info.getTransField(TranslateType.DICTIONARY)) {
                    field.setAccessible(true);
                    String dictType = field.getAnnotation(Translate.class).dictCode();
                    String dictCode = ReflectUtil.getStringValue(obj, field.getName());
                    String transResult = redisGet(dictType + "_" + dictCode);
                    obj.getTransMap().put(field.getName() + "Name", transResult);
                }
            } else if (TranslateType.DATABASE.equals(translateType)) {
                for (Field transField : info.getTransField(TranslateType.DATABASE)) {
                    String pkValue = ReflectUtil.getStringValue(obj, transField.getName());
                    if (StringUtil.isNotBlank(pkValue)) {
                        Translate translate = transField.getAnnotation(Translate.class);
                        // 如果有缓存，则使用缓存不查DB
                        Class<? extends ITrans> targetClass = translate.target();
                        String fieldName = translate.field();
                        List<Field> fieldList = ReflectUtil.getAnnotationField(targetClass, jakarta.persistence.Id.class);
                        if (fieldList.isEmpty()) {
                            throw new BaseException(ExceptionConstant.PRIMARY_KEY_NOT_FOUND, targetClass.getName());
                        }
                        String field = fieldList.getFirst().getName();
                        String hql = "from " + targetClass.getSimpleName() + " where " + field + "=:id";
                        TypedQuery<? extends ITrans> query = entityManager.createQuery(hql, targetClass);
                        query.setParameter(field, pkValue);
                        String fieldValue = ReflectUtil.getStringValue(query.getSingleResult(), fieldName);
                        obj.getTransMap().put(fieldName, fieldValue);
                    }
                }
            }
        }
    }

    /**
     * 刷新缓存
     *
     * @param dictType 字典分组编码
     * @param dicMap   字典map
     */
    public void refreshCache(String dictType, Map<String, String> dicMap) {
        dicMap.forEach((k, v) -> redisPut(dictType + "_" + k, v));
    }

    /**
     * 添加缓存
     */
    void redisPut(String key, String value) {
        RedisUtil.setCacheObject(REDIS_PREFIX_TRANS + key, value);
    }

    /**
     * 获取缓存
     */
    String redisGet(String key) {
        return StringUtil.isNotBlank(key) ? RedisUtil.getCacheObject(REDIS_PREFIX_TRANS + key) : null;
    }

}
