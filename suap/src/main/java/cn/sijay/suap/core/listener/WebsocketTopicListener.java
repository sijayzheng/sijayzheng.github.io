package cn.sijay.suap.core.listener;

import cn.sijay.suap.core.utils.WebsocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;

/**
 * <strong>WebsocketTopicListener</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Slf4j
public class WebsocketTopicListener implements ApplicationRunner, Ordered {

    /**
     * 在Spring Boot应用程序启动时初始化WebSocket主题订阅监听器
     *
     * @param args 应用程序参数
     */
    @Override
    public void run(ApplicationArguments args) {
        // 订阅WebSocket消息
        WebsocketUtil.subscribeMessage((message) -> {
            log.info("WebSocket主题订阅收到消息session keys={} message={}", message.getUserIds(), message.getMessage());
            // 如果key不为空就按照key发消息 如果为空就群发
            if (message.getUserIds().isEmpty()) {
                WebsocketUtil.getSessionsAll().forEach(key -> WebsocketUtil.sendMessage(key, message.getMessage()));
            } else {
                message.getUserIds().forEach(key -> {
                    if (WebsocketUtil.existSession(key)) {
                        WebsocketUtil.sendMessage(key, message.getMessage());
                    }
                });
            }
        });
        log.info("初始化WebSocket主题订阅监听器成功");
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
