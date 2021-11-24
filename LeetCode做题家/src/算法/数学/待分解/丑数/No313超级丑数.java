package 算法.数学.待分解.丑数;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class No313超级丑数 {

    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(1L);
        Set<Long> set = new HashSet<>();
        while (n > 1) {
            long min = priorityQueue.poll();
            for (int prime : primes) {
                if (set.add(min * prime)) {
                    priorityQueue.add(min * prime);
                }
            }
            n--;
        }
        return Math.toIntExact(priorityQueue.poll());
    }

    public int nthSuperUglyNumber2(int n, int[] primes) {
        int[] dp = new int[n];
        // 初始值都为0
        int[] cursor = new int[primes.length];
        dp[0] = 1;
        int cur = 1;
        while (cur < n) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < primes.length; i++) {
                min = Math.min(primes[i] * dp[cursor[i]], min);
            }
            for (int i = 0; i < primes.length; i++) {
                if (min == dp[cursor[i]] * primes[i]) {
                    cursor[i]++;
                }
            }
            dp[cur] = min;
            cur++;
        }
        return dp[n - 1];
    }
}
