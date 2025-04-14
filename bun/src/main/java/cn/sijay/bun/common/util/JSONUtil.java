package cn.sijay.bun.common.util;

import cn.sijay.bun.common.exception.BaseException;
import cn.sijay.bun.common.exception.ExceptionConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <strong>JSONUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-11-07
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JSONUtil {
    private static final ObjectMapper OBJECT_MAPPER = SpringUtil.getBean(ObjectMapper.class);

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(text, clazz);
        } catch (IOException e) {
            throw new BaseException(ExceptionConstant.JSON_PARSE_ERROR, e.getMessage());
        }
    }

    public static <T> T parseObject(byte[] bytes, Class<T> clazz) {
        if (bytes == null || bytes.length == 0 || clazz == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(bytes, clazz);
        } catch (IOException e) {
            throw new BaseException(ExceptionConstant.JSON_PARSE_ERROR, e.getMessage());
        }
    }

    public static <T> T parseObject(String text, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(text, typeReference);
        } catch (IOException e) {
            throw new BaseException(ExceptionConstant.JSON_PARSE_ERROR, e.getMessage());
        }
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        if (StringUtils.isEmpty(text)) {
            return new ArrayList<>();
        }
        try {
            return OBJECT_MAPPER.readValue(text, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            throw new BaseException(ExceptionConstant.JSON_PARSE_ERROR, e.getMessage());
        }
    }

    public static String toJSONString(Object object) {
        if (ObjectUtils.isEmpty(object)) {
            return "";
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new BaseException(ExceptionConstant.JSON_SERIAL_ERROR, e.getMessage());
        }
    }

    public static String toJSONPrettyString(Object object) {
        if (ObjectUtils.isEmpty(object)) {
            return "";
        }
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new BaseException(ExceptionConstant.JSON_SERIAL_ERROR, e.getMessage());
        }
    }
}

