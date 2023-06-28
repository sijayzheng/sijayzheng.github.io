package sijay.zheng.z.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目17：二叉树的中序遍历
 * <p>给定一个二叉树的根节点 <code>root</code> ，返回 <em>它的 <strong>中序</strong>遍历</em> 。</p>
 *
 * <p></p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/inorder_1.jpg" style="height: 200px; width: 125px;" />
 * <pre>
 * <strong>输入：</strong>root = [1,null,2,3]
 * <strong>输出：</strong>[1,3,2]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [1]
 * <strong>输出：</strong>[1]
 * </pre>
 *
 * <p></p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点数目在范围 <code>[0, 100]</code> 内</li>
 * <li><code>-100 &lt;= Node.val &lt;= 100</code></li>
 * </ul>
 *
 * <p></p>
 *
 * <p><strong>进阶:</strong>递归算法很简单，你可以通过迭代算法完成吗？</p>
 */
class Q17 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode treeNode = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5, new TreeNode(7), new TreeNode(8))), new TreeNode(3, null, new TreeNode(6)));
        solution.inorderTraversal(treeNode);
    }


    //Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root != null) {
                list.addAll(inorderTraversal(root.left));
                list.add(root.val);
                list.addAll(inorderTraversal(root.right));
            }
            return list;
        }

    }
}
