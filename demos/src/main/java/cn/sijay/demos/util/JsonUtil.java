package cn.sijay.demos.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <strong>JsonUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-07-23
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {
    public static final JsonMapper OBJECT_MAPPER = new JsonMapper();

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    public static String toJsonString(Object object) {
        if (Objects.isNull(object)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("序列化错误", e);
        }
    }

    public static String toPrettyJsonString(Object object) {
        if (Objects.isNull(object)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("序列化错误", e);
        }
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        if (text.isEmpty()) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(text, clazz);
        } catch (IOException e) {
            throw new RuntimeException("反序列化错误", e);
        }
    }

    public static <T> T parseObject(byte[] bytes, Class<T> clazz) {
        if (bytes == null || bytes.length == 0 || clazz == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(bytes, clazz);
        } catch (IOException e) {
            throw new RuntimeException("反序列化错误", e);
        }
    }

    public static <T> T parseObject(String text, TypeReference<T> typeReference) {
        if (text.isBlank()) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(text, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("反序列化错误", e);
        }
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        if (text.isEmpty()) {
            return new ArrayList<>();
        }
        try {
            return OBJECT_MAPPER.readValue(text, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            throw new RuntimeException("反序列化错误", e);
        }
    }
}
