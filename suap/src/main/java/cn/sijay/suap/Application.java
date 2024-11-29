package cn.sijay.suap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <p>
 * <em>Application</em>
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
//@EnableScheduling
//@EnableWebSocket
@EnableJpaRepositories
@EnableJpaAuditing
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info(">>>>>启动成功<<<<<");
    }

}
