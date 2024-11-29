package cn.sijay.suap.core.handler;

import cn.sijay.suap.core.entity.WebsocketMessage;
import cn.sijay.suap.core.utils.WebsocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

/**
 * <strong>WebSocketHandler</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-07-18
 */
@Slf4j
public class WebsocketHandler extends AbstractWebSocketHandler {
    /**
     * 连接成功后
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
//        SysUser loginUser = (SysUser) session.getAttributes().get(LOGIN_USER_KEY);
//        WebsocketUtil.addSession(loginUser.getId(), session);
//        log.info("[connect] sessionId: {},userId:{}", session.getId(), loginUser.getId());
    }

    /**
     * 处理接收到的文本消息
     *
     * @param session WebSocket会话
     * @param message 接收到的文本消息
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        // 从WebSocket会话中获取登录用户信息
//        SysUser loginUser = (SysUser) session.getAttributes().get(LOGIN_USER_KEY);

        // 创建WebSocket消息DTO对象
        WebsocketMessage webSocketMessageDto = new WebsocketMessage();
//        webSocketMessageDto.setUserIds(List.of(loginUser.getId()));
        webSocketMessageDto.setMessage(message.getPayload());
        WebsocketUtil.publishMessage(webSocketMessageDto);
    }

    /**
     * 处理接收到的二进制消息
     *
     * @param session WebSocket会话
     * @param message 接收到的二进制消息
     * @throws Exception 处理消息过程中可能抛出的异常
     */
    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        super.handleBinaryMessage(session, message);
    }

    /**
     * 处理接收到的Pong消息（心跳监测）
     *
     * @param session WebSocket会话
     * @param message 接收到的Pong消息
     */
    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) {
        WebsocketUtil.sendPongMessage(session);
    }

    /**
     * 处理WebSocket传输错误
     *
     * @param session   WebSocket会话
     * @param exception 发生的异常
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.error("[transport error] sessionId: {} , exception:{}", session.getId(), exception.getMessage());
    }

    /**
     * 在WebSocket连接关闭后执行清理操作
     *
     * @param session WebSocket会话
     * @param status  关闭状态信息
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
//        SysUser loginUser = (SysUser) session.getAttributes().get(LOGIN_USER_KEY);
//        WebsocketUtil.removeSession(loginUser.getId());
//        log.info("[disconnect] sessionId: {},userId:{}", session.getId(), loginUser.getId());
    }

    /**
     * 指示处理程序是否支持接收部分消息
     *
     * @return 如果支持接收部分消息，则返回true；否则返回false
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
