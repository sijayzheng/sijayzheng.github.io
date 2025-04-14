package cn.sijay.biu.core.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * SseMessage
 *
 * @author Sijay
 * @since 2025-02-18
 */
@Data
public class SseMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String message;
    private List<Long> userIds;

    public SseMessage() {
    }

    public SseMessage(String message, List<Long> userIds) {
        this.message = message;
        this.userIds = userIds;
    }

    public SseMessage(String message, Long... userIds) {
        this.message = message;
        this.userIds = List.of(userIds);
    }
}
