package cn.sijay.suap;

import cn.sijay.suap.core.util.LogUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * <em>Application</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/11 11:41
 */
@MapperScan("cn.sijay.suap.**.mapper")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        LogUtil.success("启动成功");
    }

}
