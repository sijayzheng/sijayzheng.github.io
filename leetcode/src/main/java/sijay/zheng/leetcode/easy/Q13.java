package sijay.zheng.leetcode.easy;

/**
 * 题目13：x 的平方根
 * <p>给你一个非负整数 <code>x</code> ，计算并返回<code>x</code>的 <strong>算术平方根</strong> 。</p>
 *
 * <p>由于返回类型是整数，结果只保留 <strong>整数部分 </strong>，小数部分将被 <strong>舍去 。</strong></p>
 *
 * <p><strong>注意：</strong>不允许使用任何内置指数函数和算符，例如 <code>pow(x, 0.5)</code> 或者 <code>x ** 0.5</code> 。</p>
 *
 * <p></p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>x = 4
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>x = 8
 * <strong>输出：</strong>2
 * <strong>解释：</strong>8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 * </pre>
 *
 * <p></p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= x &lt;= 2<sup>31</sup> - 1</code></li>
 * </ul>
 */
class Q13 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        for (int i = 0; i < 50; i++) {
            System.out.println(solution.mySqrt(i));
        }
    }

    static class Solution {
        public int mySqrt(int x) {
            if (0 == x) {
                return 0;
            }
            if (x >= 2147395600) {
                return 46340;
            }
            Integer a = 0, b = 2;
            while (true) {
                if (a * a < x && x < b * b) {
                    System.out.println(a);
                    return a + 1;
                } else {
                    a++;
                    b++;
                }
            }
        }
    }
}
