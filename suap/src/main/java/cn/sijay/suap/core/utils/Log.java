package cn.sijay.suap.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <strong>Log</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Log {
    public static void error(String msg) {
        System.out.println("033[1;31m>>>>>" + msg + "<<<<<033[0m");
    }

    public static void success(String msg) {
        System.out.println("033[1;32m>>>>>" + msg + "<<<<<033[0m");
    }

    public static void warn(String msg) {
        System.out.println("033[1;33m>>>>>" + msg + "<<<<<033[0m");
    }

    public static void info(String msg) {
        System.out.println("033[1;34m>>>>>" + msg + "<<<<<033[0m");
    }

    public static void attention(String msg) {
        System.out.println("033[1;35m>>>>>" + msg + "<<<<<033[0m");
    }
}
