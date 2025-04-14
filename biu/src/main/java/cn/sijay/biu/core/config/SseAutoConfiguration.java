package cn.sijay.biu.core.config;

import cn.sijay.biu.auth.controller.SseController;
import cn.sijay.biu.auth.listener.SseTopicListener;
import cn.sijay.biu.core.handler.SseHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * SSE 自动装配
 *
 * @author Sijay
 */
@AutoConfiguration
@ConditionalOnProperty(value = "config.sse.enabled", havingValue = "true")
public class SseAutoConfiguration {

    @Bean
    public SseHandler sseHandler() {
        return new SseHandler();
    }

    @Bean
    public SseTopicListener sseTopicListener() {
        return new SseTopicListener();
    }

    @Bean
    public SseController sseController(SseHandler sseHandler) {
        return new SseController(sseHandler);
    }

}
