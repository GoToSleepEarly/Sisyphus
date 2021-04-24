package 数据结构.链表;

import basic.ListNode;

public class No21合并两个有序链表 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null || l2 != null) {
            int num1 = l1 == null ? 101 : l1.val;
            int num2 = l2 == null ? 101 : l2.val;
            if (num1 < num2) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
