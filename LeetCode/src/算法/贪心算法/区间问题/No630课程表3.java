package 算法.贪心算法.区间问题;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class No630课程表3 {

    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(c -> c[1]));
        int sum = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        for (int[] c : courses) {
            // 先塞
            priorityQueue.add(c[0]);
            sum+=c[0];
            while(!priorityQueue.isEmpty() && sum>c[1]){
                sum-=priorityQueue.poll();
            }
        }
        return priorityQueue.size();
    }

}
