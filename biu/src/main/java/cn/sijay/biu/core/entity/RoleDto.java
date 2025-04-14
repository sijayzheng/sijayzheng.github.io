package cn.sijay.biu.core.entity;

import cn.sijay.biu.core.enums.DataScopeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * RoleDto
 *
 * @author Sijay
 * @since 2025-02-24
 */
@Data
@NoArgsConstructor
public class RoleDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色权限
     */
    private String code;

    /**
     * 数据范围
     */
    private DataScopeEnum dataScope;

}
