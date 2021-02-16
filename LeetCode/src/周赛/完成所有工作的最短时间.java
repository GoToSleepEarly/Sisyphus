package 周赛;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 完成所有工作的最短时间 {
    public static void main(String[] args) {
        完成所有工作的最短时间 s = new 完成所有工作的最短时间();
        s.minimumTimeRequired(new int[]{1, 3, 5, 1000}, 4);
    }

    public int minimumTimeRequired(int[] jobs, int k) {
        Arrays.sort(jobs);
        int lastIndex = jobs.length - 1;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            queue.add(jobs[lastIndex]);
            lastIndex--;
        }
        while (lastIndex != -1) {
            queue.add(queue.poll() + jobs[lastIndex]);
            lastIndex--;
        }
        int res = 0;
        while (!queue.isEmpty()) {
            res = queue.poll();
        }
        return res;

    }
}
