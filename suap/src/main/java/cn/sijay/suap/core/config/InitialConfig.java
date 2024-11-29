package cn.sijay.suap.core.config;

import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.stp.StpLogic;
import cn.sijay.suap.core.handler.KeyPrefixHandler;
import cn.sijay.suap.core.handler.WebsocketHandler;
import cn.sijay.suap.core.interceptor.WebsocketInterceptor;
import cn.sijay.suap.core.listener.WebsocketTopicListener;
import cn.sijay.suap.core.properties.ConfigProperties;
import cn.sijay.suap.core.properties.RedissonProperties;
import cn.sijay.suap.core.utils.SpringUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.client.codec.StringCodec;
import org.redisson.codec.CompositeCodec;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.redisson.spring.starter.RedissonAutoConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.VirtualThreadTaskExecutor;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * <strong>InitialConfig</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class InitialConfig {
    private final ConfigProperties configProperties;
    private final RedissonProperties redissonProperties;
    //    private final IDictService dictService;

    //初始化变量
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info(">>>>>系统初始化<<<<<");
//        new File(configProperties.getTempFolder()).mkdir();
//        new File(configProperties.getFileStorage()).mkdir();
        log.info(">>>>>系统字典初始化<<<<<");
//        dictService.initDictData();

    }

    // Sa-Token 整合 jwt (Simple 简单模式)
    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForSimple();
    }

    @Bean
    public RedissonAutoConfigurationCustomizer redissonCustomizer() {
        return config -> {
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));
            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
            ObjectMapper om = new ObjectMapper();
            om.registerModule(javaTimeModule);
            om.setTimeZone(TimeZone.getDefault());
            om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            // 指定序列化输入的类型，类必须是非final修饰的。序列化时将对象全类名一起保存下来
            om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
            TypedJsonJacksonCodec jsonCodec = new TypedJsonJacksonCodec(Object.class, om);
            // 组合序列化 key 使用 String 内容使用通用 json 格式
            CompositeCodec codec = new CompositeCodec(StringCodec.INSTANCE, jsonCodec, jsonCodec);
            config.setThreads(redissonProperties.getThreads())
                  .setNettyThreads(redissonProperties.getNettyThreads())
                  // 缓存 Lua 脚本 减少网络传输(redisson 大部分的功能都是基于 Lua 脚本实现)
                  .setUseScriptCache(true)
                  .setCodec(codec);
            if (SpringUtil.isVirtual()) {
                config.setNettyExecutor(new VirtualThreadTaskExecutor("redisson-"));
            }
            // 使用单机模式
            config.useSingleServer()
                  //设置redis key前缀
                  .setNameMapper(new KeyPrefixHandler(redissonProperties.getKeyPrefix()))
                  .setTimeout(redissonProperties.getTimeout())
                  .setClientName(redissonProperties.getClientName())
                  .setIdleConnectionTimeout(redissonProperties.getIdleConnectionTimeout())
                  .setSubscriptionConnectionPoolSize(redissonProperties.getSubscriptionConnectionPoolSize())
                  .setConnectionMinimumIdleSize(redissonProperties.getConnectionMinimumIdleSize())
                  .setConnectionPoolSize(redissonProperties.getConnectionPoolSize());
            log.info(">>>>>初始化redis配置<<<<<");
        };
    }

    @Bean
    public WebSocketConfigurer webSocketConfigurer(HandshakeInterceptor handshakeInterceptor, WebSocketHandler webSocketHandler) {
        // 返回一个WebsocketConfigurer对象，用于配置Websocket
        log.info(">>>>>初始化websocket配置<<<<<");
        return registry -> registry
            // 添加Websocket处理程序和拦截器到指定路径，设置允许的跨域来源
            .addHandler(new WebsocketHandler(), "/websocket")
            .addInterceptors(new WebsocketInterceptor())
            .setAllowedOrigins("*");
    }

    @Bean
    public HandshakeInterceptor handshakeInterceptor() {
        return new WebsocketInterceptor();
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new WebsocketHandler();
    }

    @Bean
    public WebsocketTopicListener topicListener() {
        return new WebsocketTopicListener();
    }

}
