package cn.sijay.biu.core.config;

import cn.sijay.biu.core.annotation.TranslationType;
import cn.sijay.biu.core.handler.TranslationBeanSerializerModifier;
import cn.sijay.biu.core.handler.TranslationHandler;
import cn.sijay.biu.core.translation.TranslationInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 翻译模块配置类
 *
 * @author Sijay
 */
@Slf4j
@AutoConfiguration
public class TranslationConfig {

    private final List<TranslationInterface<?>> list;

    private final ObjectMapper objectRepository;

    public TranslationConfig(List<TranslationInterface<?>> list, ObjectMapper objectRepository) {
        this.list = list;
        this.objectRepository = objectRepository;
    }

    @PostConstruct
    public void init() {
        Map<String, TranslationInterface<?>> map = new HashMap<>(list.size());
        for (TranslationInterface<?> trans : list) {
            if (trans.getClass().isAnnotationPresent(TranslationType.class)) {
                TranslationType annotation = trans.getClass().getAnnotation(TranslationType.class);
                map.put(annotation.type(), trans);
            } else {
                log.warn(trans.getClass().getName() + " 翻译实现类未标注 TranslationType 注解!");
            }
        }
        TranslationHandler.TRANSLATION_MAP.putAll(map);
        // 设置 Bean 序列化修改器
        objectRepository.setSerializerFactory(
                objectRepository.getSerializerFactory()
                                .withSerializerModifier(new TranslationBeanSerializerModifier()));
    }

}
