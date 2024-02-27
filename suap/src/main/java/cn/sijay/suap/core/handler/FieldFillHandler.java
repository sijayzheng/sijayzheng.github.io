package cn.sijay.suap.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * <p>
 * <em>ffh</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/8 17:09
 */
public class FieldFillHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (getFieldValByName("creator", metaObject) == null) {
            this.fillStrategy(metaObject, "creator", 1L);
        }
        if (getFieldValByName("createTime", metaObject) == null) {
            this.fillStrategy(metaObject, "createTime", LocalDateTime.now());
        }
        if (getFieldValByName("version", metaObject) == null) {
            this.fillStrategy(metaObject, "version", 1);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (getFieldValByName("updater", metaObject) == null) {
            this.fillStrategy(metaObject, "updater", 1L);
        }
        if (getFieldValByName("updateTime", metaObject) == null) {
            this.fillStrategy(metaObject, "updateTime", LocalDateTime.now());
        }
    }
}
