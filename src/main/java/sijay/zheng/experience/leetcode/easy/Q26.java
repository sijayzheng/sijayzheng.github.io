package sijay.zheng.experience.leetcode.easy;

import com.alibaba.fastjson2.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目26：杨辉三角 II
 * <p>给定一个非负索引 <code>rowIndex</code>，返回「杨辉三角」的第 <code>rowIndex</code><em> </em>行。</p>
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
 * <strong>输入:</strong> rowIndex = 3
 * <strong>输出:</strong> [1,3,3,1]
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> rowIndex = 0
 * <strong>输出:</strong> [1]
 * </pre>
 *
 * <p><strong>示例 3:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> rowIndex = 1
 * <strong>输出:</strong> [1,1]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>0 <= rowIndex <= 33</code></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong></p>
 *
 * <p>你可以优化你的算法到 <code><em>O</em>(<i>rowIndex</i>)</code> 空间复杂度吗？</p>
 */
class Q26 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(JSON.toJSONString(solution.getRow(6)));
    }

    static class Solution {
        public List<Integer> getRow(int rowIndex) {
            List<List<Integer>> result = new ArrayList<>();
            for (int i = 0; i <= rowIndex; i++) {
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
            return result.get(rowIndex);
        }
    }
}