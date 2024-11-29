package cn.sijay.suap.core.utils;

import cn.sijay.suap.core.base.BaseException;
import cn.sijay.suap.core.constant.Constants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.app.Velocity;

import java.util.Properties;

/**
 * <strong>VelocityUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VelocityUtil {
    /**
     * 初始化vm方法
     */
    public static void initVelocity() {
        try {
            Properties p = new Properties();
            // 加载classpath目录下的vm文件
            p.setProperty("resource.loader.file.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty(Velocity.INPUT_ENCODING, Constants.UTF8);
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        } catch (Exception e) {
            throw new BaseException("Velocity引擎初始化失败");
        }
    }

}
