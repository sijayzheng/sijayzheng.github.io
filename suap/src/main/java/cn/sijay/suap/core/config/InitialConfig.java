package cn.sijay.suap.core.config;

import cn.sijay.suap.core.properties.ConfigProperties;
import cn.sijay.suap.core.utils.PrintUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

/**
 * <strong>InitialConfig</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ConfigProperties.class)
public class InitialConfig {
    final private ConfigProperties configProperties;

    //初始化变量
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        PrintUtil.info("系统初始化");
    }
}
