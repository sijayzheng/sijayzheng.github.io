package cn.sijay.suap.core.config;

import cn.sijay.suap.core.handler.BigNumberSerializer;
import cn.sijay.suap.core.utils.PrintUtil;
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
import org.springframework.context.annotation.Primary;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * <strong>JacksonConfig</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Slf4j
@AutoConfiguration(before = JacksonAutoConfiguration.class)
public class JacksonConfig {
    @Bean
    @Primary
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
            PrintUtil.success("初始化 jackson 配置");
        };
    }
}

