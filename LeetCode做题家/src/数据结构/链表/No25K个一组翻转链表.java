package 数据结构.链表;

import basic.ListNode;

public class No25K个一组翻转链表 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        new No25K个一组翻转链表().reverseKGroup(head, 2);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curNode = head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode preHead = dummy;
        while (true) {
            // 寻找end
            ListNode end = curNode;
            for (int i = 0; i < k; i++) {
                // 不足k个
                if (end == null) {
                    preHead.next = curNode;
                    return dummy.next;
                }
                end = end.next;
            }
            // end为要翻转的最后一个节点
            preHead = reverseK(preHead, curNode, end);
            curNode = end;
        }
    }

    public ListNode reverseK(ListNode preHead, ListNode head, ListNode end) {
        // 最终返回last
        //preHead不需要变
        ListNode pre = null;
        ListNode toReturn = head;
        while (head != end) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        preHead.next = pre;
        // 被反转的最后一个节点
        return toReturn;
    }

}
