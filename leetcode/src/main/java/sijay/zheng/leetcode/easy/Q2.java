package sijay.zheng.leetcode.easy;

/**
 * 题目2：回文数
 * <p>给你一个整数 <code>x</code> ，如果 <code>x</code> 是一个回文整数，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
 *
 * <p>回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。</p>
 *
 * <ul>
 * <li>例如，<code>121</code> 是回文，而 <code>123</code> 不是。</li>
 * </ul>
 *
 * <p></p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>x = 121
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>x = -121
 * <strong>输出：</strong>false
 * <strong>解释：</strong>从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>x = 10
 * <strong>输出：</strong>false
 * <strong>解释：</strong>从右向左读, 为 01 。因此它不是一个回文数。
 * </pre>
 *
 * <p></p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>-2<sup>31</sup>&lt;= x &lt;= 2<sup>31</sup>- 1</code></li>
 * </ul>
 *
 * <p></p>
 *
 * <p><strong>进阶：</strong>你能不将整数转为字符串来解决这个问题吗？</p>
 */
class Q2 {
    public static void main(String[] args) {

        Solution solution = new Solution();
        int x1 = 121;
        //输出：true
        System.out.println(solution.isPalindrome2(x1));
        int x2 = -121;
        //输出：false
        System.out.println(solution.isPalindrome2(x2));
        int x3 = 10;
        //输出：false
        System.out.println(solution.isPalindrome2(x3));
        System.out.println(solution.isPalindrome2(10001));
    }

    static class Solution {
        public boolean isPalindrome(int x) {
            if (x < 0 || x % 10 == 0) {
                return false;
            } else {
                char[] chars = String.valueOf(x).toCharArray();
                for (int i = 0, j = chars.length - 1; i <= j; i++, j--) {
                    if (chars[i] != chars[j]) {
                        return false;
                    }
                }
            }
            return true;
        }

        public boolean isPalindrome2(int x) {
            if (x == 0) {
                return true;
            } else if (x < 0 || x % 10 == 0) {
                return false;
            } else {
                int tmp = x;
                int a = 0;
                while (tmp > 0) {
                    a = a * 10 + tmp % 10;
                    tmp = tmp / 10;
                }
                return a == x;
            }
        }
    }
}
