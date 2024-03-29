package sijay.zheng.leetcode.easy;

/**
 * 题目14：爬楼梯
 * <p>假设你正在爬楼梯。需要 <code>n</code>阶你才能到达楼顶。</p>
 *
 * <p>每次你可以爬 <code>1</code> 或 <code>2</code> 个台阶。你有多少种不同的方法可以爬到楼顶呢？</p>
 *
 * <p></p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 2
 * <strong>输出：</strong>2
 * <strong>解释：</strong>有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 3
 * <strong>输出：</strong>3
 * <strong>解释：</strong>有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 * </pre>
 *
 * <p></p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= n &lt;= 45</code></li>
 * </ul>
 */
class Q14 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        long start = System.currentTimeMillis();
        int i = solution.climbStairs(45);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(i);
    }

    static class Solution {
        public int climbStairs(int n) {
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 2;
            }
            int[] arr = new int[46];
            arr[1] = 1;
            arr[2] = 2;
            for (int i = 3; i < 46; i++) {
                arr[i] = arr[i - 1] + arr[i - 2];
            }
            return arr[n];
        }
    }
}
