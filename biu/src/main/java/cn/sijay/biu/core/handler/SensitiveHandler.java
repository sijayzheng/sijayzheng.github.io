package cn.sijay.biu.core.handler;

import cn.dev33.satoken.stp.StpUtil;
import cn.sijay.biu.core.annotation.Sensitive;
import cn.sijay.biu.core.enums.SensitiveStrategy;
import cn.sijay.biu.core.helper.LoginHelper;
import cn.sijay.biu.core.util.StringUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

/**
 * 数据脱敏json序列化工具
 *
 * @author Sijay
 */
@Slf4j
public class SensitiveHandler extends JsonSerializer<String> implements ContextualSerializer {

    private SensitiveStrategy strategy;
    private String roleKey;
    private String perms;

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (isSensitive(roleKey, perms)) {
            gen.writeString(strategy.desensitizer().apply(value));
        } else {
            gen.writeString(value);
        }
    }

    boolean isSensitive(String roleKey, String perms) {
        if (!LoginHelper.isLogin()) {
            return true;
        }
        boolean roleExist = StringUtil.isNotBlank(roleKey);
        boolean permsExist = StringUtil.isNotBlank(perms);
        if (roleExist && permsExist) {
            if (StpUtil.hasRole(roleKey) && StpUtil.hasPermission(perms)) {
                return false;
            }
        } else if (roleExist && StpUtil.hasRole(roleKey)) {
            return false;
        } else if (permsExist && StpUtil.hasPermission(perms)) {
            return false;
        }
        return !LoginHelper.isSuperAdmin();
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        Sensitive annotation = property.getAnnotation(Sensitive.class);
        if (Objects.nonNull(annotation) && Objects.equals(String.class, property.getType().getRawClass())) {
            this.strategy = annotation.strategy();
            this.roleKey = annotation.roleKey();
            this.perms = annotation.perms();
            return this;
        }
        return prov.findValueSerializer(property.getType(), property);
    }
}
