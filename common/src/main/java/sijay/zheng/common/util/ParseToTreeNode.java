package sijay.zheng.common.util;

/**
 * @author sijay
 * @description ParseToTreeNode
 * @date 2023/10/9 21:28
 */
public interface ParseToTreeNode<T, K> {
    /**
     * @param entity   实体类
     * @param treeNode 树节点
     */
    void parse(T entity, TreeNode<K> treeNode);

}
