package 周赛;

import java.util.LinkedList;
import java.util.List;

public class 找出临界点之间的最小和最大距离 {

    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode pre = head;
        ListNode cur = head.next;
        List<Integer> values = new LinkedList<>();
        int index = 2;
        int min = Integer.MAX_VALUE;
        while (cur.next != null) {
            int preV = pre.val;
            int curV = cur.val;
            int nextV = cur.next.val;
            if ((curV < preV && curV < nextV) || (curV > preV && curV > nextV)) {
                if (values.size() >= 1) {
                    min = Math.min(min, index - values.get(values.size() - 1));
                }
                values.add(index);
            }
            index++;
            pre = cur;
            cur = cur.next;
        }
        if (values.size() < 2) {
            return new int[]{-1, -1};
        }
        return new int[]{min, values.get(values.size() - 1) - values.get(0)};
    }
}
