package sijay.zheng.experience.common.entity;

import lombok.*;

import java.util.*;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
