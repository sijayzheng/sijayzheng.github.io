package sijay.zheng.leetcode.easy;

import com.alibaba.fastjson2.JSON;

/**
 * 题目11：加一
 * <p>给定一个由 <strong>整数 </strong>组成的<strong> 非空</strong> 数组所表示的非负整数，在该数的基础上加一。</p>
 *
 * <p>最高位数字存放在数组的首位， 数组中每个元素只存储<strong>单个</strong>数字。</p>
 *
 * <p>你可以假设除了整数 0 之外，这个整数不会以零开头。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>digits = [1,2,3]
 * <strong>输出：</strong>[1,2,4]
 * <strong>解释：</strong>输入数组表示数字 123。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>digits = [4,3,2,1]
 * <strong>输出：</strong>[4,3,2,2]
 * <strong>解释：</strong>输入数组表示数字 4321。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>digits = [0]
 * <strong>输出：</strong>[1]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= digits.length <= 100</code></li>
 * <li><code>0 <= digits[i] <= 9</code></li>
 * </ul>
 */
class Q11 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] digits1 = new int[]{1, 2, 3};
        System.out.println(JSON.toJSONString(solution.plusOne(digits1)));
        //输出：[1,2,4]
        int[] digits2 = new int[]{4, 3, 2, 1};
        System.out.println(JSON.toJSONString(solution.plusOne(digits2)));
        //输出：[4,3,2,2]
        int[] digits3 = new int[]{0};
        System.out.println(JSON.toJSONString(solution.plusOne(digits3)));
        //输出：[1]
        int[] digits4 = new int[]{9};
        System.out.println(JSON.toJSONString(solution.plusOne(digits4)));
        //输出：[1,0]
    }

    static class Solution {
        public int[] plusOne(int[] digits) {
            int i = digits.length - 1;
            int tmp = 1;
            for (; i >= 0; i--) {
                if (tmp == 0) {
                    break;
                }
                digits[i] = digits[i] + tmp;
                if (digits[i] > 9) {
                    digits[i] = 0;
                    tmp = 1;
                } else {
                    tmp = 0;
                }
            }
            if (tmp == 1) {
                int[] result = new int[digits.length + 1];
                result[0] = 1;
                for (int j = 0; j < digits.length; j++) {
                    result[j + 1] = digits[j];
                }
                return result;
            }
            return digits;
        }
    }
}
