package 算法.数学.待分解.丑数;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class No264丑数2 {
    public static void main(String[] args) {
        new No264丑数2().nthUglyNumber2(10);
    }
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(1L);
        Set<Long> set = new HashSet<>();
        int[] arr = new int[]{2, 3, 5};
        while (n > 1) {
            long top = priorityQueue.poll();
            for (int i : arr) {
                long next = top * i;
                if (set.add(next)) {
                    priorityQueue.add(next);
                }
            }
            n--;
        }
        return Math.toIntExact(priorityQueue.poll());
    }

    public int nthUglyNumber2(int n) {
        int[] dp = new int[n];
        // 对应dp的下标
        int a = 0;
        int b = 0;
        int c = 0;
        int cur = 1;
        dp[0] = 1;
        while (cur < n) {
            int max = Math.min(Math.min(dp[a] * 2, dp[b] * 3), dp[c] * 5);
            if (max == dp[a] * 2) {
                a++;
            }
            if (max == dp[b] * 3) {
                b++;
            }
            if (max == dp[c] * 5) {
                c++;
            }
            dp[cur++] = max;
        }
        return dp[n - 1];
    }
}
