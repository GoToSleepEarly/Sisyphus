package 周赛;

public class 交换链表中的节点 {

    public static void main(String[] args) {
        交换链表中的节点 s = new 交换链表中的节点();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        s.swapNodes(head, 2);
    }

    public ListNode swapNodes(ListNode head, int k) {
        // 定位第正k个，并得出总数
        int cur = 1;
        int total = 0;
        ListNode firstK = head;
        ListNode curNode = head;
        while (curNode != null) {
            if (cur == k) {
                firstK = curNode;
            }
            curNode = curNode.next;
            cur++;
            total++;
        }

        // 寻找倒数第k个
        int lastKIndex = total - k + 1;
        curNode = head;
        ListNode lastK = head;
        for (int i = 1; i < lastKIndex; i++) {
            curNode = curNode.next;
        }
        lastK = curNode;
        int tmp = lastK.val;
        lastK.val = firstK.val;
        firstK.val = tmp;
        return head;
    }
}

