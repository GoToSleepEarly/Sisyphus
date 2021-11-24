package 数据结构.链表;

import basic.ListNode;

public class 交换链表中的节点 {
    /**
     * 链表题目，经典的思路是快慢指针+哑结点+数学优化
     * 1、快慢指针可以通过路径关系得到所需结果
     * 2、哑结点可以方便交换节点
     * 3、可以暴力通过多次遍历，也可以通过1中的路径优化
     */
    public ListNode swapNodes(ListNode head, int k) {
        // 定位到两个ListNode后，直接交换值即可
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            n++;
        }
        ListNode node1 = head;
        int cnt1 = 1;
        while (cnt1 < k) {
            node1 = node1.next;
            cnt1++;
        }
        ListNode node2 = head;
        k = n - k + 1;
        int cnt2 = 1;
        while (cnt2 < k) {
            node2 = node2.next;
            cnt2++;
        }
        // 交换
        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
        return head;
    }

    public ListNode swapNodes2(ListNode head, int k) {
        // 快慢指针，k和n-k的路径和为k
        // 也就是跑到第k个节点时，满指针归0，快指针到终点时就结束了。
        ListNode fast = head;
        int cnt = 1;
        while (cnt < k) {
            fast = fast.next;
            cnt++;
        }
        ListNode kNode = fast;
        // fast到达k，满指针开始跑
        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // 交换
        int tmp = kNode.val;
        kNode.val = slow.val;
        slow.val = tmp;
        return head;
    }
}
