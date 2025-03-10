package cn.sijay.bun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BunApplication {

    public static void main(String[] args) {
        SpringApplication.run(BunApplication.class, args);
        System.out.println("👌");
    }

}
