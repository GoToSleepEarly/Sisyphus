package 数据结构.链表;

import basic.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class No23合并K个升序链表 {

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(l -> l.val));
        for (ListNode list : lists) {
            if (list != null) {
                priorityQueue.add(list);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (!priorityQueue.isEmpty()) {
            ListNode min = priorityQueue.poll();
            cur.next = min;
            cur = min;
            if (min.next != null) {
                priorityQueue.add(min.next);
            }
        }
        return dummy.next;
    }
}
