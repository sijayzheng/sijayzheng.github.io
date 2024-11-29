package sijay.zheng.leetcode.easy;

/**
 * 题目4：最长公共前缀
 * <p>编写一个函数来查找字符串数组中的最长公共前缀。</p>
 *
 * <p>如果不存在公共前缀，返回空字符串<code>""</code>。</p>
 *
 * <p></p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>strs = ["flower","flow","flight"]
 * <strong>输出：</strong>"fl"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>strs = ["dog","racecar","car"]
 * <strong>输出：</strong>""
 * <strong>解释：</strong>输入不存在公共前缀。</pre>
 *
 * <p></p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= strs.length &lt;= 200</code></li>
 * <li><code>0 &lt;= strs[i].length &lt;= 200</code></li>
 * <li><code>strs[i]</code> 仅由小写英文字母组成</li>
 * </ul>
 */
class Q4 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strs1 = new String[]{"flower", "flow", "flight"};
        System.out.println(solution.longestCommonPrefix(strs1));

        String[] strs2 = new String[]{"dog", "racecar", "car"};
        System.out.println(solution.longestCommonPrefix(strs2));

        String[] strs3 = new String[]{"ab", "a"};
        System.out.println(solution.longestCommonPrefix(strs3));

    }

    static class Solution {
        public String longestCommonPrefix(String[] strs) {
            String str = strs[0];
            String result = "";
            for (int i = 0; i < str.length(); i++) {
                char tmp = str.charAt(i);
                for (int j = 1; j < strs.length; j++) {
                    if (strs[j].length() <= i) {
                        return result;
                    }
                    if (strs[j].charAt(i) != tmp) {
                        return result;
                    }
                }
                result += tmp;
            }
            return result;
        }
    }
}
