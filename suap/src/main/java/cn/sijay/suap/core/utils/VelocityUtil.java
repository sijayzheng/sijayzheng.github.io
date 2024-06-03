package cn.sijay.suap.core.utils;

import cn.sijay.suap.core.constant.Constants;
import cn.sijay.suap.core.exception.BaseException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;

/**
 * <strong>VelocityUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VelocityUtil {
    /**
     * 初始化vm方法
     */
    public static void initVelocity() {
        Properties p = new Properties();
        try {
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

    public static String render(String template, VelocityContext context) {
        StringWriter sw = new StringWriter();
        Template tpl = Velocity.getTemplate("/templates/" + template, Constants.UTF8);
        tpl.merge(context, sw);
        return sw.toString();
    }
}
