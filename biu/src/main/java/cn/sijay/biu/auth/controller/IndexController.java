package cn.sijay.biu.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * IndexController
 *
 * @author Sijay
 * @since 2025-04-01
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String idx() {
        return "index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
