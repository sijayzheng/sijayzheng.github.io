package sijay.zheng.experience.log.log4j;

import org.apache.logging.log4j.*;

/**
 * @author Sijay
 */
public class Log4jTest {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger("LogTest");
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");
        // OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE > ALL
    }
}