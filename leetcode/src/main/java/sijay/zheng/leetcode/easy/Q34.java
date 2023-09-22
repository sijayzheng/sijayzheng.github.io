package sijay.zheng.leetcode.easy;

import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * 题目34：Excel表列名称
 * <p>给你一个整数 <code>columnNumber</code> ，返回它在 Excel 表中相对应的列名称。</p>
 *
 * <p>例如：</p>
 *
 * <pre>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>columnNumber = 1
 * <strong>输出：</strong>"A"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>columnNumber = 28
 * <strong>输出：</strong>"AB"
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>columnNumber = 701
 * <strong>输出：</strong>"ZY"
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>columnNumber = 2147483647
 * <strong>输出：</strong>"FXSHRXW"
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= columnNumber <= 2<sup>31</sup> - 1</code></li>
 * </ul>
 */
class Q34 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.convertToTitle(1));
        //输出："A"

        System.out.println(solution.convertToTitle(28));
        //输出："AB"

        System.out.println(solution.convertToTitle(701));
        //输出："ZY"

        System.out.println(solution.convertToTitle(2147483647));
        //输出："FXSHRXW"
    }

    static class Solution {
        public String convertToTitle(int columnNumber) {
            char[] az = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
            LinkedList<Character> list = new LinkedList<>();
            while (columnNumber > 1) {
                list.add(az[columnNumber % 26 - 1]);
                columnNumber = columnNumber / 26;
            }
            if (columnNumber > 0) {
                list.add(az[columnNumber - 1]);
            }
            Collections.reverse(list);
            return list.stream().map(String::valueOf).collect(Collectors.joining());
        }
    }
}
