package sijay.zheng.z.leetcode.easy;

/**
 * 题目21：将有序数组转换为二叉搜索树
 * <p>给你一个整数数组 <code>nums</code> ，其中元素已经按 <strong>升序</strong> 排列，请你将其转换为一棵 <strong>高度平衡</strong> 二叉搜索树。</p>
 *
 * <p><strong>高度平衡 </strong>二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。</p>
 *
 * <p></p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/18/btree1.jpg" style="width: 302px; height: 222px;" />
 * <pre>
 * <strong>输入：</strong>nums = [-10,-3,0,5,9]
 * <strong>输出：</strong>[0,-3,9,-10,null,5]
 * <strong>解释：</strong>[0,-10,5,null,-3,null,9] 也将被视为正确答案：
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/18/btree2.jpg" style="width: 302px; height: 222px;" />
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/18/btree.jpg" style="width: 342px; height: 142px;" />
 * <pre>
 * <strong>输入：</strong>nums = [1,3]
 * <strong>输出：</strong>[3,1]
 * <strong>解释：</strong>[1,null,3] 和 [3,1] 都是高度平衡二叉搜索树。
 * </pre>
 *
 * <p></p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
 * <li><code>nums</code> 按 <strong>严格递增</strong> 顺序排列</li>
 * </ul>
 */
class Q21 {
    public static void main(String[] args) {
        Solution solution = new Solution();
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
        public TreeNode sortedArrayToBST(int[] nums) {
            return new TreeNode();
        }
    }
}
