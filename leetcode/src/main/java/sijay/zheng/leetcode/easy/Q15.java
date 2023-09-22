package sijay.zheng.leetcode.easy;

/**
 * 题目15：删除排序链表中的重复元素
 * <p>给定一个已排序的链表的头<meta charset="UTF-8" /><code>head</code>，<em>删除所有重复的元素，使每个元素只出现一次</em>。返回 <em>已排序的链表</em>。</p>
 *
 * <p></p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/01/04/list1.jpg" style="height: 160px; width: 200px;" />
 * <pre>
 * <strong>输入：</strong>head = [1,1,2]
 * <strong>输出：</strong>[1,2]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/01/04/list2.jpg" style="height: 123px; width: 300px;" />
 * <pre>
 * <strong>输入：</strong>head = [1,1,2,3,3]
 * <strong>输出：</strong>[1,2,3]
 * </pre>
 *
 * <p></p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>链表中节点数目在范围 <code>[0, 300]</code> 内</li>
 * <li><code>-100 &lt;= Node.val &lt;= 100</code></li>
 * <li>题目数据保证链表已经按升序 <strong>排列</strong></li>
 * </ul>
 */
class Q15 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head1 = new ListNode(1, new ListNode(1, new ListNode(2)));
//输出：[1,2]
        solution.deleteDuplicates(head1);
        while (head1.val >= -100) {
            System.out.println(head1.val);
            head1 = head1.next;
            if (head1 == null) {
                break;
            }
        }
        System.out.println("----------------");
        ListNode head2 = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(3, new ListNode(3))))));
//输出：[1,2,3]
        solution.deleteDuplicates(head2);
        while (head2.val >= -100) {
            System.out.println(head2.val);
            head2 = head2.next;
            if (head2 == null) {
                break;
            }
        }

    }
    //Definition for singly-linked list.

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    static class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode currNode = head;
            while (currNode != null && currNode.next != null) {
                if (currNode.val == currNode.next.val) {
                    currNode.next = currNode.next.next;
                } else {
                    currNode = currNode.next;
                }
            }
            return head;
        }
    }
}
