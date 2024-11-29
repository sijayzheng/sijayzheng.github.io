package cn.sijay.suap.core.utils;

import cn.sijay.suap.core.entity.WebsocketMessage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import static cn.sijay.suap.core.constant.Constants.WEB_SOCKET_TOPIC;

/**
 * <strong>WebsocketUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebsocketUtil {

    private static final Map<Long, WebSocketSession> USER_SESSION_MAP = new ConcurrentHashMap<>();

    /**
     * 将WebSocket会话添加到用户会话Map中
     *
     * @param userId  会话键，用于检索会话
     * @param session 要添加的WebSocket会话
     */
    public static void addSession(Long userId, WebSocketSession session) {
        USER_SESSION_MAP.put(userId, session);
    }

    /**
     * 从用户会话Map中移除指定会话键对应的WebSocket会话
     *
     * @param userId 要移除的会话键
     */
    public static void removeSession(Long userId) {
        if (USER_SESSION_MAP.containsKey(userId)) {
            try (WebSocketSession ignored = USER_SESSION_MAP.remove(userId)) {
                log.debug("关闭会话");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 根据会话键从用户会话Map中获取WebSocket会话
     *
     * @param userId 要获取的会话键
     * @return 与给定会话键对应的WebSocket会话，如果不存在则返回null
     */
    public static WebSocketSession getSessions(Long userId) {
        return USER_SESSION_MAP.get(userId);
    }

    /**
     * 获取存储在用户会话Map中所有WebSocket会话的会话键集合
     *
     * @return 所有WebSocket会话的会话键集合
     */
    public static Set<Long> getSessionsAll() {
        return USER_SESSION_MAP.keySet();
    }

    /**
     * 检查给定的会话键是否存在于用户会话Map中
     *
     * @param userId 要检查的会话键
     * @return 如果存在对应的会话键，则返回true；否则返回false
     */
    public static Boolean existSession(Long userId) {
        return USER_SESSION_MAP.containsKey(userId);
    }

    /**
     * 向指定的WebSocket会话发送消息
     *
     * @param userId  要发送消息的用户id
     * @param message 要发送的消息内容
     */
    public static void sendMessage(Long userId, String message) {
        WebSocketSession session = WebsocketUtil.getSessions(userId);
        sendMessage(session, message);
    }

    /**
     * 订阅WebSocket消息主题，并提供一个消费者函数来处理接收到的消息
     *
     * @param consumer 处理WebSocket消息的消费者函数
     */
    public static void subscribeMessage(Consumer<WebsocketMessage> consumer) {
        RedisUtil.subscribe(WEB_SOCKET_TOPIC, WebsocketMessage.class, consumer);
    }

    public static void publishMessage(List<Long> userIds, String message) {
        publishMessage(new WebsocketMessage(userIds, message));
    }

    /**
     * 发布WebSocket订阅消息
     *
     * @param webSocketMessage 要发布的WebSocket消息对象
     */
    public static void publishMessage(WebsocketMessage webSocketMessage) {
        List<Long> unsentSessionKeys = new ArrayList<>();
        // 当前服务内session,直接发送消息
        for (Long userId : webSocketMessage.getUserIds()) {
            if (WebsocketUtil.existSession(userId)) {
                WebsocketUtil.sendMessage(userId, webSocketMessage.getMessage());
                continue;
            }
            unsentSessionKeys.add(userId);
        }
        // 不在当前服务内session,发布订阅消息
        if (!unsentSessionKeys.isEmpty()) {
            WebsocketMessage broadcastMessage = new WebsocketMessage();
            broadcastMessage.setMessage(webSocketMessage.getMessage());
            broadcastMessage.setUserIds(unsentSessionKeys);
            RedisUtil.publish(WEB_SOCKET_TOPIC, broadcastMessage, consumer -> log.info(" WebSocket发送主题订阅消息topic:{} session keys:{} message:{}", WEB_SOCKET_TOPIC, unsentSessionKeys, webSocketMessage.getMessage()));
        }
    }

    /**
     * 向所有的WebSocket会话发布订阅的消息(群发)
     *
     * @param message 要发布的消息内容
     */
    public static void publishAll(String message) {
        WebsocketMessage broadcastMessage = new WebsocketMessage();
        broadcastMessage.setMessage(message);
        RedisUtil.publish(WEB_SOCKET_TOPIC, broadcastMessage, consumer -> log.info("WebSocket发送主题订阅消息topic:{} message:{}", WEB_SOCKET_TOPIC, message));
    }

    /**
     * 向指定的WebSocket会话发送Pong消息
     *
     * @param session 要发送Pong消息的WebSocket会话
     */
    public static void sendPongMessage(WebSocketSession session) {
        sendMessage(session, new PongMessage());
    }

    /**
     * 向指定的WebSocket会话发送文本消息
     *
     * @param session WebSocket会话
     * @param message 要发送的文本消息内容
     */
    public static void sendMessage(WebSocketSession session, String message) {
        sendMessage(session, new TextMessage(message));
    }

    /**
     * 向指定的WebSocket会话发送WebSocket消息对象
     *
     * @param session WebSocket会话
     * @param message 要发送的WebSocket消息对象
     */
    private static void sendMessage(WebSocketSession session, WebSocketMessage<?> message) {
        if (session == null || !session.isOpen()) {
            log.warn("[send] session会话已经关闭");
        } else {
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                log.error("[send] session({}) 发送消息({}) 异常", session, message, e);
            }
        }
    }
}
