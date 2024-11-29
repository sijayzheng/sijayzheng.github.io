package cn.sijay.suap.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * <strong>Route</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Route implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 路由名字
     */
    private String name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 其他元素
     */
    private Meta meta;

    /**
     * 子路由
     */
    private List<Route> children;

}

