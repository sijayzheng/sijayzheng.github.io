package cn.sijay.biu.core.util;

import cn.sijay.biu.core.exception.UtilException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

/**
 * JsonUtil
 *
 * @author Sijay
 * @since 2025-02-20
 */
@RequiredArgsConstructor
public class JsonUtil {
    private static final ObjectMapper mapper = SpringUtil.getBean(ObjectMapper.class);

    /**
     * 将对象转换为 JSON 字符串
     *
     * @param obj 要转换的对象
     * @return JSON 字符串
     */
    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new UtilException("对象转换为 JSON 字符串时出错", e);
        }
    }

    /**
     * 将对象转换为 JSON 字符串
     *
     * @param obj 要转换的对象
     * @return JSON 字符串
     */
    public static String toPrettyJson(Object obj) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new UtilException("对象转换为 JSON 字符串时出错", e);
        }
    }

    /**
     * 将 JSON 字符串转换为指定类型的对象
     *
     * @param json  JSON 字符串
     * @param clazz 目标对象的类类型
     * @param <T>   泛型类型
     * @return 指定类型的对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new UtilException("JSON 字符串转换为对象时出错", e);
        }
    }

    /**
     * 将 JSON 字符串转换为指定类型的对象，支持复杂类型
     *
     * @param json          JSON 字符串
     * @param typeReference 类型引用
     * @param <T>           泛型类型
     * @return 指定类型的对象
     */
    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new UtilException("JSON 字符串转换为对象时出错", e);
        }
    }
}
