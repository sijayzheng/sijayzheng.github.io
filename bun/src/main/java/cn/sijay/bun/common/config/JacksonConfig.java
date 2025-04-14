package cn.sijay.bun.common.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.NumberSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

/**
 * <strong>JacksonConfig</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-11-15
 */
@Slf4j
@AutoConfiguration(before = JacksonAutoConfiguration.class)
public class JacksonConfig {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            // 全局配置序列化返回 JSON 处理
            DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
            builder.modules(new JavaTimeModule().addSerializer(Long.class, BigNumberSerializer.INSTANCE)
                                                .addSerializer(Long.TYPE, BigNumberSerializer.INSTANCE)
                                                .addSerializer(BigInteger.class, BigNumberSerializer.INSTANCE)
                                                .addSerializer(BigDecimal.class, ToStringSerializer.instance)
                                                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER))
                                                .addSerializer(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER))
                                                .addSerializer(LocalTime.class, new LocalTimeSerializer(TIME_FORMATTER))

                                                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER))
                                                .addDeserializer(LocalDate.class, new LocalDateDeserializer(DATE_FORMATTER))
                                                .addDeserializer(LocalTime.class, new LocalTimeDeserializer(TIME_FORMATTER)));
            builder.timeZone(TimeZone.getDefault());
            builder.locale(Locale.SIMPLIFIED_CHINESE);
            log.info(">>>>> jackson配置初始化 <<<<<");
        };
    }

    @JacksonStdImpl
    static class BigNumberSerializer extends NumberSerializer {

        /**
         * 提供实例
         */
        public static final BigNumberSerializer INSTANCE = new BigNumberSerializer(Number.class);
        /**
         * 根据 JS Number.MAX_SAFE_INTEGER 与 Number.MIN_SAFE_INTEGER 得来
         */
        private static final long MAX_SAFE_INTEGER = 9007199254740991L;
        private static final long MIN_SAFE_INTEGER = -MAX_SAFE_INTEGER;

        public BigNumberSerializer(Class<? extends Number> rawType) {
            super(rawType);
        }

        @Override
        public void serialize(Number value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            // 超出范围 序列化位字符串
            if (value.longValue() > MIN_SAFE_INTEGER && value.longValue() < MAX_SAFE_INTEGER) {
                super.serialize(value, gen, provider);
            } else {
                gen.writeString(value.toString());
            }
        }

    }

}
