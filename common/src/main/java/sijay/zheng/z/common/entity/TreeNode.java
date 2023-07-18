/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.common.entity;

import lombok.Data;

import java.util.List;

/**
 * 树形结构的父类
 *
 * @author sijay
 * @date 2021-12-13
 */
@Data
public class TreeNode<T> {
    private Integer id;
    private Integer pid;
    //子节点
    private List<T> children;
}
