package sijay.zheng.experience.mybatisplus;

import org.mybatis.spring.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

/**
 * @author Sijay
 */
@SpringBootApplication
@MapperScan("zheng.sijay.mybatisplus.mapper")
public class LearnMybatisPlus01 {

    public static void main(String[] args) {
        SpringApplication.run(LearnMybatisPlus01.class, args);
    }

}