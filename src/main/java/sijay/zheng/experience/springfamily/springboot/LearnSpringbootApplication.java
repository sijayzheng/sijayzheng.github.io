package sijay.zheng.experience.springfamily.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sijay
 */
@SpringBootApplication
@MapperScan("zheng.sijay.mapper")
public class LearnSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnSpringbootApplication.class, args);
    }

}