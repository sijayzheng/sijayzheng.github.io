package cn.sijay.biu.auth.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.sijay.biu.auth.entity.Captcha;
import cn.sijay.biu.auth.entity.LoginParam;
import cn.sijay.biu.auth.entity.LoginUser;
import cn.sijay.biu.auth.service.LoginService;
import cn.sijay.biu.core.annotation.ApiEncrypt;
import cn.sijay.biu.core.base.BaseController;
import cn.sijay.biu.core.entity.Result;
import cn.sijay.biu.core.entity.SseMessage;
import cn.sijay.biu.core.handler.SseHandler;
import cn.sijay.biu.core.helper.LoginHelper;
import cn.sijay.biu.core.properties.CommonProperties;
import cn.sijay.biu.core.util.ImageUtil;
import cn.sijay.biu.core.util.RedisUtil;
import cn.sijay.biu.core.util.UuidUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.Random;

/**
 * AuthController
 *
 * @author Sijay
 * @since 2025-02-14
 */
@Slf4j
@SaIgnore
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

    private final LoginService loginService;
    private final CommonProperties commonProperties;
    private final SseHandler sseHandler;

    /**
     * 生成验证码
     */
    @GetMapping("/code")
    public Result<Captcha> getCode() {
        // 生成验证码字符串
        Random random = new Random();
        StringBuilder captchaText = new StringBuilder();
        // 创建BufferedImage对象
        BufferedImage image = new BufferedImage(120, 40, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        // 设置背景颜色
        g2d.setColor(Color.lightGray);
        g2d.fillRect(0, 0, 120, 40);
        // 设置字体
        Font font = new Font("Arial", Font.BOLD, 30);
        g2d.setFont(font);
        // 设置字体颜色 绘制验证码字符
        for (int i = 0; i < 4; i++) {
            String ch = Character.toString(random.nextInt(26) + 64);
            captchaText.append(ch);
            g2d.setColor(ImageUtil.getRandomColor());
            g2d.drawString(ch, (i * 25) + 20, 30);
        }
        // 绘制干扰线
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(120);
            int y1 = random.nextInt(40);
            int x2 = random.nextInt(120);
            int y2 = random.nextInt(40);
            g2d.setColor(ImageUtil.getRandomColor());
            g2d.drawLine(x1, y1, x2, y2);
        }
        g2d.dispose();
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", bos);
            byte[] imageBytes = bos.toByteArray();
            // 保存验证码信息
            String uuid = UuidUtil.getUuid();
            Captcha captcha = new Captcha();
            captcha.setEnabled(commonProperties.getCaptcha().isEnable());
            captcha.setUuid(uuid);
            captcha.setImg("data:image/png;base64," + Base64.getEncoder().encodeToString(imageBytes));
            RedisUtil.set("", captchaText, Duration.ofSeconds(commonProperties.getCaptcha().getExpire()));
            return success(captcha);
        } catch (IOException e) {
            e.printStackTrace();
            return fail("验证码生成失败");
        }
    }

    /**
     * 登录方法
     *
     * @return 结果
     */
    @ApiEncrypt
    @PostMapping("/login")
    public Result<String> login(@RequestBody @Validated LoginParam param) {
        // 登录
        String token = loginService.login(param);
        Long userId = LoginHelper.getUserId();
        Thread.startVirtualThread(() -> {
            SseMessage sseMessage = new SseMessage("欢迎", userId);
            sseHandler.publishMessage(sseMessage);
        });
        return success("登录成功", token);
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        loginService.logout();
        return success("退出成功");
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/userinfo")
    public Result<LoginUser> userinfo() {
        return success(LoginHelper.getLoginUser());
    }

}
