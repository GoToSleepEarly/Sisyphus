package 周赛;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class 交换链表中的节点 {

    public static void main(String[] args) {
        List<Integer> s = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        Iterator<Integer> itr = s.iterator();
        while (itr.hasNext()){
            int x = itr.next();
            if(x == 3){
                itr.remove();
            }
        }
        System.out.println(s);
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

