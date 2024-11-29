package sijay.zheng.leetcode.easy;

/**
 * 题目5：有效的括号
 * <p>给定一个只包括 <code>'('</code>，<code>')'</code>，<code>'{'</code>，<code>'}'</code>，<code>'['</code>，<code>']'</code>的字符串 <code>s</code> ，判断字符串是否有效。</p>
 *
 * <p>有效字符串需满足：</p>
 *
 * <ol>
 * <li>左括号必须用相同类型的右括号闭合。</li>
 * <li>左括号必须以正确的顺序闭合。</li>
 * <li>每个右括号都有一个对应的相同类型的左括号。</li>
 * </ol>
 *
 * <p></p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "()"
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "()[]{}"
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "(]"
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p></p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>s</code> 仅由括号 <code>'()[]{}'</code> 组成</li>
 * </ul>
 */
class Q5 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid("([}}])"));
        //输出：false

        System.out.println(solution.isValid("()"));
        //输出：true

        System.out.println(solution.isValid("()[]{}"));
        //输出：true

        System.out.println(solution.isValid("(]"));
        //输出：false
    }

    static class Solution {
        public boolean isValid(String s) {
            if (s.length() % 2 == 0) {
                char[] chars = new char[s.length() / 2];
                int n = 0;
                char tmp;
                for (int i = 0; i < s.length(); i++) {
                    tmp = s.charAt(i);
                    if ('(' == tmp || '{' == tmp || '[' == tmp) {
                        if (n >= chars.length) {
                            return false;
                        }
                        chars[n] = tmp;
                        n++;
                    } else {
                        if (n == 0) {
                            return false;
                        }
                        if (']' == tmp && chars[n - 1] == '[') {
                            n--;
                        } else if (')' == tmp && chars[n - 1] == '(') {
                            n--;
                        } else if ('}' == tmp && chars[n - 1] == '{') {
                            n--;
                        } else {
                            return false;
                        }
                    }
                }
                return n <= 0;
            } else {
                return false;
            }
        }
    }
}
