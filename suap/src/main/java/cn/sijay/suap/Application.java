package cn.sijay.suap;

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
 * @since 2024/1/11 11:41
 */
@EnableJpaRepositories
@EnableJpaAuditing
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        LogUtil.success("启动成功");
    }

}
