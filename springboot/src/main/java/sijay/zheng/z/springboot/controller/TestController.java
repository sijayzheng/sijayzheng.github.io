package sijay.zheng.z.springboot.controller;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sijay.zheng.z.springboot.config.TestConfig;

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
