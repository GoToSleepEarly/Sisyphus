package 算法.贪心算法.待整理;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 消灭怪物的最大数量 {

    /**
     * 直接模拟法的贪心，利用优先队列或者排序都行
     * <p>
     * 一般来说，如果模拟过程中需要动态调整，那么优先队列更合适
     */
    public int eliminateMaximum(int[] dist, int[] speed) {

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(x -> (double) x[0] / x[1]));
        int n = dist.length;
        for (int i = 0; i < n; i++) {
            priorityQueue.add(new int[]{dist[i], speed[i]});
        }
        int minute = 0;
        while (!priorityQueue.isEmpty()) {
            int[] cur = priorityQueue.peek();
            if (cur[0] <= cur[1] * minute) {
                return minute;
            }
            priorityQueue.poll();
            minute++;
        }
        return minute;
    }
}
