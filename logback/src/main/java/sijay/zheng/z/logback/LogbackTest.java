package sijay.zheng.z.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sijay
 * @date 2023/2/12 21:14
 */
public class LogbackTest {
    static Logger logger = LoggerFactory.getLogger(LogbackTest.class);

    public static void main(String[] args) {
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        // OFF >  ERROR > WARN > INFO > DEBUG > TRACE > ALL
    }
}
