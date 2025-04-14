package cn.sijay.demos.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * <strong>TestController</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-12-13
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("upload")
    public void upload(HttpServletRequest request, @RequestBody Map<String, Object> reqMap) {
        try (FileOutputStream fos = new FileOutputStream("D:\\temp\\out.pdf")) {
            System.out.println(reqMap.get("name"));
            Object o = reqMap.get("fileContent");
            System.out.println(o);
            System.out.println(o.getClass().getName());
            if (o instanceof byte[] bytes) {
                System.out.println("o length is " + bytes.length);
                fos.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
