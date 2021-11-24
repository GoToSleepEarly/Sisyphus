package 周赛;

import java.util.Collections;
import java.util.PriorityQueue;

public class 移除石子使总数最小 {
    public static void main(String[] args) {
        new 移除石子使总数最小().minStoneSum(new int[]{5,4,9},2);
    }
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        for (int pile : piles) {
            priorityQueue.add(pile);
        }
        while (k > 0) {
            int cur = priorityQueue.poll();
            priorityQueue.add((int) Math.ceil(cur/2.0));
            k--;
        }
        int sum = 0;
        for (Integer integer : priorityQueue) {
            sum += integer;
        }
        return sum;
    }
}
