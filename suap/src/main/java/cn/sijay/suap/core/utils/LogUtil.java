package cn.sijay.suap.core.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * <strong>LogUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Slf4j
public class LogUtil {
    public static void error(String msg) {
        log.info("\033[1;31m>>>>>" + msg + "<<<<<\033[0m");
    }

    public static void success(String msg) {
        log.info("\033[1;32m>>>>>" + msg + "<<<<<\033[0m");
    }

    public static void warn(String msg) {
        log.info("\033[1;33m>>>>>" + msg + "<<<<<\033[0m");
    }

    public static void info(String msg) {
        log.info("\033[1;34m>>>>>" + msg + "<<<<<\033[0m");
    }

    public static void attention(String msg) {
        log.info("\033[1;35m>>>>>" + msg + "<<<<<\033[0m");
    }

    public static void infoLog(String msg, Object... params) {
        if (params == null) {
            log.info(msg);
        } else {
            log.info(msg, params);
        }
    }

    public static void warnLog(String msg, Object... params) {
        if (params == null) {
            log.warn(msg);
        } else {
            log.warn(msg, params);
        }
    }

    public static void errorLog(String msg, Object... params) {
        if (params == null) {
            log.error(msg);
        } else {
            log.error(msg, params);
        }
    }
}
