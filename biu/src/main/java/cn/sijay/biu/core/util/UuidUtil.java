package cn.sijay.biu.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * UuidUtil
 *
 * @author Sijay
 * @since 2025-02-14
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UuidUtil {
    public static String getUuid() {
        return UUID.randomUUID().toString();
    }

    public static String getSimpleUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
