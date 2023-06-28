/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sijay
 */
@SpringBootApplication
public class ZApplication {
    static Logger logger = LoggerFactory.getLogger(ZApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ZApplication.class, args);
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }
}
