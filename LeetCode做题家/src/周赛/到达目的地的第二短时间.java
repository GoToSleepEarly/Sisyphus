package 周赛;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class 到达目的地的第二短时间 {

    public int secondMinimum(int n, int[][] edges, int time, int change) {
        // 尝试bfs
        // 初始绿灯，change分钟改变一次
        boolean isRed = false;
        int remain = change;
        // 小根堆保存结果
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        // 队列
        Deque<int[]> queue = new LinkedList<>();
        queue.addLast(new int[]{0, 0, 1});
        while (!queue.isEmpty()) {
            int[] cur = queue.pollFirst();
            int curIndex = cur[0];
            int curTime = cur[1];
            if (curIndex == n - 1) {
                if (priorityQueue.size() < 2) {
                    priorityQueue.add(curTime);
                }
                if (priorityQueue.peek() > curTime) {
                    priorityQueue.poll();
                    priorityQueue.add(curTime);
                }
            }
            int[] nexts = edges[curIndex];
            int nextTime = isRed ? curTime + time + remain : curTime + time;
            int status = cur[2];
            for (int next : nexts) {
                if (status >> (next - 1) == 1) {
                    continue;
                }
                int nextStatus = status | (1 << (next - 1));
                queue.addLast(new int[]{next - 1, nextTime, nextStatus});
            }
            // 改变红绿灯
            if (remain - time < 0) {
                isRed = !isRed;
                remain = remain + change - time;
            }
        }
        return priorityQueue.peek();
    }
}
