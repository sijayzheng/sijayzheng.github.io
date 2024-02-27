package cn.sijay.suap.core.interfaces;

import cn.sijay.suap.core.entity.TreeNode;

/**
 * <p>
 * <em>ParseToTreeNode</em>
 * </p>
 *
 * @author Sijay
 * @since 2024/1/25 15:32
 */
public interface ParseToTreeNode<T, K> {
    /**
     * @param entity   实体类
     * @param treeNode 树节点
     */
    void parse(T entity, TreeNode<K> treeNode);

}