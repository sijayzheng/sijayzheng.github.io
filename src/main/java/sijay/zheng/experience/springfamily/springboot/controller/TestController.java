package sijay.zheng.experience.springfamily.springboot.controller;

import com.alibaba.fastjson2.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import sijay.zheng.experience.springfamily.springboot.config.*;

/**
 * @author Sijay
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestConfig testConfig;

    @GetMapping("/test")
    public void test() {
        System.out.println("test");
        System.out.println(testConfig.getTesta());
        System.out.println(testConfig.getTestb());
        System.out.println(JSON.toJSONString(testConfig.getUsers()));
    }

    @PostMapping("/test")
    public void testp() {
        System.out.println("test");
        System.out.println(testConfig.getTesta());
        System.out.println(testConfig.getTestb());
        System.out.println(JSON.toJSONString(testConfig.getUsers()));
    }

}