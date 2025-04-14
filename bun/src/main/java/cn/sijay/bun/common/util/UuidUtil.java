package cn.sijay.bun.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * <strong>UuidUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-11-20
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UuidUtil {
    public static String fastUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String uuid() {
        return UUID.randomUUID().toString();
    }
}
