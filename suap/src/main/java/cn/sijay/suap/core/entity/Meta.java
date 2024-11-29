package cn.sijay.suap.core.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * <strong>Meta</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Data
public class Meta implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 内链地址（http(s)://开头）
     */
    private String link;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否显示，当设置 true 的时候该路由不会再侧边栏出现
     */
    private boolean visible = true;

}
