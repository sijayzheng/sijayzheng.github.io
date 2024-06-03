package cn.sijay.suap.core.entity;

import cn.sijay.suap.core.utils.ValidateUtil;
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
 * @since 2024-06-01
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
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;

    /**
     * 设置为true，则不会被 <keep-alive>缓存
     */
    private boolean noCache;

    /**
     * 内链地址（http(s)://开头）
     */
    private String link;

    public Meta(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

    public Meta(String title, String icon, String link) {
        this.title = title;
        this.icon = icon;
        this.link = link;
    }

    public Meta(String title, String icon, boolean noCache, String link) {
        this.title = title;
        this.icon = icon;
        this.noCache = !noCache;
        if (ValidateUtil.isHttp(link)) {
            this.link = link;
        }
    }

}
