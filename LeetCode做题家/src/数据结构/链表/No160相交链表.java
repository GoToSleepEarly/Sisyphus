package 数据结构.链表;

import basic.ListNode;

public class No160相交链表 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while (a != null && b != null) {
            a = a.next;
            b = b.next;
        }
        // 把剩下的跑完
        ListNode c = a == null ? headB : headA;
        ListNode d = a == null ? b : a;
        while (d != null) {
            c = c.next;
            d = d.next;
        }
        // 一起跑
        ListNode e = a == null ? headA : headB;
        while (e != c) {
            e = e.next;
            c = c.next;
        }
        return e;
    }
}
