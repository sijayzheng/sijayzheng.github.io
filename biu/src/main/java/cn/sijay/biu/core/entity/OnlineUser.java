package cn.sijay.biu.core.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 当前在线会话
 *
 * @author Sijay
 */

@Data
@NoArgsConstructor
public class OnlineUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 会话编号
     */
    private String tokenId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 登录时间
     */
    private Long loginTime;

}
