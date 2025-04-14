package cn.sijay.biu.core.handler;

import cn.sijay.biu.core.util.SpringUtil;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

/**
 * 获取所有Url配置
 *
 * @author Sijay
 */
@Data
public class AllUrlHandler implements InitializingBean {

    private List<String> urls = new ArrayList<>();

    @Override
    public void afterPropertiesSet() {
        Set<String> set = new HashSet<>();
        RequestMappingHandlerMapping mapping = SpringUtil.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        map.keySet().forEach(info -> {
            // 获取注解上边的 path 替代 path variable 为 *
            Objects.requireNonNull(info.getPathPatternsCondition().getPatterns())
                   .forEach(url -> set.add(
                           url.getPatternString().replaceAll("\\{(.*?)}", "*")));
        });
        urls.addAll(set);
    }

}
