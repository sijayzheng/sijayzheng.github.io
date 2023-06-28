package sijay.zheng.z.leetcode.easy;

import com.alibaba.fastjson2.JSON;

import java.util.HashMap;

/**
 * 题目1：两数之和
 * <p>给定一个整数数组 <code>nums</code>和一个整数目标值 <code>target</code>，请你在该数组中找出 <strong>和为目标值 </strong><em><code>target</code></em> 的那<strong>两个</strong>整数，并返回它们的数组下标。</p>
 *
 * <p>你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。</p>
 *
 * <p>你可以按任意顺序返回答案。</p>
 *
 * <p></p>
 *
 * <p><strong class="example">示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [2,7,11,15], target = 9
 * <strong>输出：</strong>[0,1]
 * <strong>解释：</strong>因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * </pre>
 *
 * <p><strong class="example">示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [3,2,4], target = 6
 * <strong>输出：</strong>[1,2]
 * </pre>
 *
 * <p><strong class="example">示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [3,3], target = 6
 * <strong>输出：</strong>[0,1]
 * </pre>
 *
 * <p></p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>2 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
 * <li><code>-10<sup>9</sup> &lt;= target &lt;= 10<sup>9</sup></code></li>
 * <li><strong>只会存在一个有效答案</strong></li>
 * </ul>
 *
 * <p></p>
 *
 * <p><strong>进阶：</strong>你可以想出一个时间复杂度小于 <code>O(n<sup>2</sup>)</code> 的算法吗？</p>
 */
class Q1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = new int[]{2, 7, 11, 15};
        int target1 = 9;
        //输出：[0,1]
        System.out.println(JSON.toJSONString(solution.twoSum2(nums1, target1)));
        int[] nums2 = new int[]{3, 2, 4};
        int target2 = 6;
        //输出：[1,2]
        System.out.println(JSON.toJSONString(solution.twoSum2(nums2, target2)));
        int[] nums3 = new int[]{3, 3};
        int target3 = 6;
        //输出：[0,1]
        System.out.println(JSON.toJSONString(solution.twoSum2(nums3, target3)));
    }

    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
                        return new int[]{i, j};
                    }
                }
            }
            return null;
        }

        public int[] twoSum2(int[] nums, int target) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(nums[i])) {
                    return new int[]{map.get(nums[i]), i};
                } else {
                    map.put(target - nums[i], i);
                }
            }
            return null;
        }
    }
}
