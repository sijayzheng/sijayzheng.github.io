package cn.zheng.sijay.j.util.tree;

import cn.zheng.sijay.j.domain.common.TreeNode;

/**
 * @author sijay
 * @desc ParseToTreeNode
 * @date 2023/12/19 14:59
 */
public interface ParseToTreeNode<T, K> {
    /**
     * @param entity   实体类
     * @param treeNode 树节点
     */
    void parse(T entity, TreeNode<K> treeNode);

}