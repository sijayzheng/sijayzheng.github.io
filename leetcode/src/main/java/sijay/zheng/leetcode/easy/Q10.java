package sijay.zheng.leetcode.easy;

/**
 * 题目10：最后一个单词的长度
 * <p>给你一个字符串 <code>s</code>，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 <strong>最后一个</strong> 单词的长度。</p>
 *
 * <p><strong>单词</strong> 是指仅由字母组成、不包含任何空格字符的最大子字符串。</p>
 *
 * <p></p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "Hello World"
 * <strong>输出：</strong>5
 * <strong>解释：</strong>最后一个单词是“World”，长度为5。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "   fly me   to   the moon  "
 * <strong>输出：</strong>4<strong>
 * 解释：</strong>最后一个单词是“moon”，长度为4。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "luffy is still joyboy"
 * <strong>输出：</strong>6
 * <strong>解释：</strong>最后一个单词是长度为6的“joyboy”。
 * </pre>
 *
 * <p></p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>s</code> 仅有英文字母和空格 <code>' '</code> 组成</li>
 * <li><code>s</code> 中至少存在一个单词</li>
 * </ul>
 */
class Q10 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.lengthOfLastWord("Hello World"));
//        输出：5
        System.out.println(solution.lengthOfLastWord("   fly me   to   the moon  "));
//        输出：4
        System.out.println(solution.lengthOfLastWord("luffy is still joyboy"));
//        输出：6
    }

    static class Solution {
        public int lengthOfLastWord(String s) {
            s = s.trim();
            int i = s.length() - 1;
            for (; i >= 0; i--) {
                if (s.charAt(i) == ' ') {
                    break;
                }
            }
            return s.substring(i + 1).length();
        }
    }
}
