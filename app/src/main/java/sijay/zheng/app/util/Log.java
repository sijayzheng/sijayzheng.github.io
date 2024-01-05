package cn.zheng.sijay.j.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sijay
 * @desc Log
 * @date 2023/12/19 14:30
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
