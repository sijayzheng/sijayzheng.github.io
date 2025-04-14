package cn.sijay.biu.auth.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import cn.sijay.biu.core.entity.Result;
import cn.sijay.biu.core.entity.SseMessage;
import cn.sijay.biu.core.handler.SseHandler;
import cn.sijay.biu.core.helper.LoginHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

/**
 * SSE 控制器
 *
 * @author Sijay
 */
@RestController
@ConditionalOnProperty(value = "sse.enabled", havingValue = "true")
@RequiredArgsConstructor
public class SseController implements DisposableBean {

    private final SseHandler sseHandler;

    /**
     * 建立 SSE 连接
     */
    @GetMapping(value = "${sse.path}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter connect() {
        String tokenValue = StpUtil.getTokenValue();
        Long userId = LoginHelper.getUserId();
        return sseHandler.connect(userId, tokenValue);
    }

    /**
     * 关闭 SSE 连接
     */
    @SaIgnore
    @GetMapping(value = "${sse.path}/close")
    public Result<Void> close() {
        String tokenValue = StpUtil.getTokenValue();
        Long userId = LoginHelper.getUserId();
        sseHandler.disconnect(userId, tokenValue);
        return Result.success();
    }

    /**
     * 向特定用户发送消息
     *
     * @param userId 目标用户的 ID
     * @param msg    要发送的消息内容
     */
    @GetMapping(value = "${sse.path}/send")
    public Result<Void> send(Long userId, String msg) {
        SseMessage dto = new SseMessage();
        dto.setUserIds(List.of(userId));
        dto.setMessage(msg);
        sseHandler.publishMessage(dto);
        return Result.success();
    }

    /**
     * 向所有用户发送消息
     *
     * @param msg 要发送的消息内容
     */
    @GetMapping(value = "${sse.path}/sendAll")
    public Result<Void> send(String msg) {
        sseHandler.publishAll(msg);
        return Result.success();
    }

    /**
     * 清理资源。此方法目前不执行任何操作，但避免因未实现而导致错误
     */
    @Override
    public void destroy() throws Exception {
        // 销毁时不需要做什么 此方法避免无用操作报错
    }

}
