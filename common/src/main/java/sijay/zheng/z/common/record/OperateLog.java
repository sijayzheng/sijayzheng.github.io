/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.common.record;

import java.time.LocalDateTime;

/**
 * @author SijayZheng
 */
public record OperateLog(
        LocalDateTime operateTime,
        Long operateUserId,
        Object inParam,
        Object outParam,
        String sourceHost,
        String requestUrl,
        String requestType
) {
    @Override
    public String toString() {
        return "OperateLog{" +
                "operateTime=" + operateTime +
                ", operateUserId=" + operateUserId +
                ", inParam=" + inParam +
                ", outParam=" + outParam +
                ", sourceHost='" + sourceHost + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", requestType='" + requestType + '\'' +
                '}';
    }
}
