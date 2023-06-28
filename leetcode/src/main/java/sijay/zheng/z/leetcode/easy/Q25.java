package sijay.zheng.z.leetcode.easy;

import com.alibaba.fastjson2.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目25：杨辉三角
 * <p>给定一个非负整数 <em><code>numRows</code>，</em>生成「杨辉三角」的前 <em><code>numRows</code> </em>行。</p>
 *
 * <p><small>在「杨辉三角」中，每个数是它左上方和右上方的数的和。</small></p>
 *
 * <p><img alt="" src="https://pic.leetcode-cn.com/1626927345-DZmfxB-PascalTriangleAnimated2.gif" /></p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> numRows = 5
 * <strong>输出:</strong> [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> numRows = 1
 * <strong>输出:</strong> [[1]]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>1 <= numRows <= 30</code></li>
 * </ul>
 */
class Q25 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(JSON.toJSONString(solution.generate(6)));
    }

    static class Solution {
        public List<List<Integer>> generate(int numRows) {
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
    }
}
