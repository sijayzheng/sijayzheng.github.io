package cn.sijay.biu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableConfigurationProperties
@EnableJpaAuditing
@EnableJpaRepositories
@EnableCaching
@SpringBootApplication
public class BiuApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiuApplication.class, args);
        System.out.println("""
                ==========
                ||启动成功||
                ==========
                """);
    }

}
