package sijay.zheng.leetcode.easy;

/**
 * 题目12：二进制求和
 * <p>给你两个二进制字符串 <code>a</code> 和 <code>b</code> ，以二进制字符串的形式返回它们的和。</p>
 *
 * <p></p>
 *
 * <p><strong>示例1：</strong></p>
 *
 * <pre>
 * <strong>输入:</strong>a = "11", b = "1"
 * <strong>输出：</strong>"100"</pre>
 *
 * <p><strong>示例2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>a = "1010", b = "1011"
 * <strong>输出：</strong>"10101"</pre>
 *
 * <p></p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= a.length, b.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>a</code> 和 <code>b</code> 仅由字符 <code>'0'</code> 或 <code>'1'</code> 组成</li>
 * <li>字符串如果不是 <code>"0"</code> ，就不含前导零</li>
 * </ul>
 */
class Q12 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.addBinary("11", "1"));
//        输出："100"
        System.out.println(solution.addBinary("1010", "1011"));
//        输出："10101"
    }

    static class Solution {
        public String addBinary(String a, String b) {
            if ("0".equals(a) && "0".equals(b)) {
                return "0";
            }
            StringBuilder sb = new StringBuilder();
            int lengtha = a.length();
            int lengthb = b.length();
            int length = Math.max(lengtha, lengthb);
            int temp = 0;
            for (int i = 1; i <= length; i++) {
                if (lengtha - i >= 0 && lengthb - i >= 0) {
                    if (a.charAt(lengtha - i) == '1' && b.charAt(lengthb - i) == '1') {
                        if (temp == 1) {
                            sb.insert(0, "1");
                        } else {
                            sb.insert(0, "0");
                        }
                        temp = 1;
                    } else if (a.charAt(lengtha - i) == '0' && b.charAt(lengthb - i) == '0') {
                        if (temp == 1) {
                            sb.insert(0, "1");
                            temp = 0;
                        } else {
                            sb.insert(0, "0");
                        }
                    } else {
                        if (temp == 1) {
                            sb.insert(0, "0");
                        } else {
                            sb.insert(0, "1");
                        }
                    }
                } else if (lengtha - i < 0) {
                    if (b.charAt(lengthb - i) == '1') {
                        if (temp == 1) {
                            sb.insert(0, "0");
                            temp = 1;
                        } else {
                            sb.insert(0, "1");
                        }
                    } else {
                        if (temp == 1) {
                            sb.insert(0, "1");
                            temp = 0;
                        } else {
                            sb.insert(0, "0");
                        }
                    }
                } else if (lengthb - i < 0) {
                    if (a.charAt(lengtha - i) == '1') {
                        if (temp == 1) {
                            sb.insert(0, "0");
                            temp = 1;
                        } else {
                            sb.insert(0, "1");
                        }
                    } else {
                        if (temp == 1) {
                            sb.insert(0, "1");
                            temp = 0;
                        } else {
                            sb.insert(0, "0");
                        }
                    }
                }
            }
            if (temp == 1) {
                sb.insert(0, "1");
            }
            return sb.toString();
        }
    }
}
