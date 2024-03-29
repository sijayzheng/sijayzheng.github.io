package sijay.zheng.leetcode.easy;

/**
 * 题目20：二叉树的最大深度
 * <p>给定一个二叉树，找出其最大深度。</p>
 *
 * <p>二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。</p>
 *
 * <p><strong>说明:</strong>叶子节点是指没有子节点的节点。</p>
 *
 * <p><strong>示例：</strong><br>
 * 给定二叉树 <code>[3,9,20,null,null,15,7]</code>，</p>
 *
 * <pre>   3
 * / \
 * 9  20
 *   /  \
 *   15   7</pre>
 *
 * <p>返回它的最大深度3 。</p>
 */
class Q20 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.maxDepth(new TreeNode(3, new TreeNode(9), new TreeNode()));
        System.out.println(i);
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
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            } else {
                return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
            }
        }
    }
}
