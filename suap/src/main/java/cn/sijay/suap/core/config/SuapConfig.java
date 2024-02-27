package cn.sijay.suap.core.config;

import cn.sijay.suap.core.properties.ConfigProperties;
import cn.sijay.suap.core.properties.GenProperties;
import cn.sijay.suap.core.util.FileUtil;
import cn.sijay.suap.core.util.LogUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

/**
 * <p>
 * <em>SuapConfig</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/31 11:29
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class SuapConfig {
    final private ConfigProperties configProperties;
    final private GenProperties genProperties;

    //初始化变量
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LogUtil.info("系统初始化");
        LogUtil.info("创建必要文件夹");
        FileUtil.mkDirOrTouch(configProperties.getTempFolder());
        FileUtil.mkDirOrTouch(configProperties.getFileStorage());
        FileUtil.mkDirOrTouch(genProperties.getPath());
    }
}
