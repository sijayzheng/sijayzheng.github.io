package sijay.zheng.experience;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(i + "---" + (i / 3) + "__" + (i % 3));
        }
    }

    /**
     * 密码要求:
     * 1.长度超过8位
     * 2.包括大小写字母.数字.其它符号,以上四种至少三种
     * 3.不能有长度大于2的包含公共元素的子串重复 （注：其他符号不含空格或换行）
     * 数据范围：输入的字符串长度满足 1≤n≤100
     * 输入描述：
     * 一组字符串。
     * 输出描述：
     * 如果符合要求输出：OK，否则输出NG
     */
    static String passwd(String str) {
        final String NG = "NG", OK = "OK";
        if (str.length() < 8) {
            return NG;
        } else {
            char[] arr = str.toCharArray();
            int[] counts = new int[]{0, 0, 0, 0};
            for (char ch : arr) {
                if (ch >= '0' && ch <= '9') {
                    counts[0] += 1;
                } else if (ch >= 'a' && ch <= 'z') {
                    counts[1] += 1;
                } else if (ch >= 'A' && ch <= 'Z') {
                    counts[2] += 1;
                } else {
                    counts[3] += 1;
                }
            }
            if (Arrays.stream(counts).filter(e -> e > 0).count() < 3) {
                return NG;
            } else {
                List<String> list = new ArrayList<>();
                Set<String> set = new HashSet<>();
                for (int i = 0; i < str.length() - 3; i++) {
                    list.add(str.substring(i, i + 3));
                    set.add(str.substring(i, i + 3));
                }
                if (list.size() == set.size()) {
                    return OK;
                } else {
                    return NG;
                }
            }
        }
    }

/*

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            if (i == 0) {
                result.add(List.of(1));
            } else if (i == 1) {
                result.add(Arrays.asList(1, 1));
            } else {
                List<Integer> row = new ArrayList<>();
                row.add(1);
                List<Integer> lastRow = result.get(i - 1);
                for (int j = 1; j < i; j++) {
                    row.add(lastRow.get(j - 1) + lastRow.get(j));
                }
                row.add(1);
                result.add(row);
            }
        }
        return result;
    }
*/

}