package sijay.zheng.leetcode.easy;

import java.util.Locale;

/**
 * 题目28：验证回文串
 * <p>如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 <strong>回文串</strong> 。</p>
 *
 * <p>字母和数字都属于字母数字字符。</p>
 *
 * <p>给你一个字符串 <code>s</code>，如果它是 <strong>回文串</strong> ，返回 <code>true</code><em> </em>；否则，返回<em> </em><code>false</code><em> </em>。</p>
 *
 * <p></p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> s = "A man, a plan, a canal: Panama"
 * <strong>输出：</strong>true
 * <strong>解释：</strong>"amanaplanacanalpanama" 是回文串。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "race a car"
 * <strong>输出：</strong>false
 * <strong>解释：</strong>"raceacar" 不是回文串。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = " "
 * <strong>输出：</strong>true
 * <strong>解释：</strong>在移除非字母数字字符之后，s 是一个空字符串 "" 。
 * 由于空字符串正着反着读都一样，所以是回文串。
 * </pre>
 *
 * <p></p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li>
 * <li><code>s</code> 仅由可打印的 ASCII 字符组成</li>
 * </ul>
 */
class Q28 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome("A man, a plan, a canal: Panama"));
        //输出：true
        System.out.println(solution.isPalindrome("race a car"));
        //输出：false
        System.out.println(solution.isPalindrome(" "));
        //输出：true

    }

    static class Solution {
        public boolean isPalindrome(String s) {
            StringBuilder st = new StringBuilder();
            StringBuilder ed = new StringBuilder();
            s = s.toLowerCase(Locale.ROOT);
            int length = s.length();
            for (int i = 0, j = length - 1; i <= j; i++, j--) {
                char h = s.charAt(i);
                char e = s.charAt(j);
                if ((h >= 48 && h <= 57) || (h >= 97 && h <= 122)) {
                    st.append(h);
                }
                if ((e >= 48 && e <= 57) || (e >= 97 && e <= 122)) {
                    ed.append(e);
                }
            }
            return st.toString().equals(ed.toString());
        }
    }
}
