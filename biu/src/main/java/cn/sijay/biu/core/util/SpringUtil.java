package cn.sijay.biu.core.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringUtil
 *
 * @author Sijay
 * @since 2025-02-20
 */
@RequiredArgsConstructor
@Component
public class SpringUtil implements ApplicationContextAware {

    @Getter
    private static ApplicationContext applicationContext;

    public static ApplicationContext context() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }
}