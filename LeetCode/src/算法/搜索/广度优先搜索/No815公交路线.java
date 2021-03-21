package 算法.搜索.广度优先搜索;

import java.util.*;

public class No815公交路线 {

    public static void main(String[] args) {
        No815公交路线 s = new No815公交路线();
        s.numBusesToDestination(new int[][]{{1, 2, 7}, {3, 6, 7}}, 5, 5);
    }

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        // 构造图会比较麻烦
        // 需要通过stop找到所属bus
        // 而通过bus找到所有stop通过route即可
        Map<Integer, List<Integer>> stop2BusMap = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            int[] route = routes[i];
            for (int stop : route) {
                stop2BusMap.computeIfAbsent(stop, k -> new LinkedList<>()).add(i);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        // 已经坐过的bus
        boolean[] visitedBus = new boolean[routes.length + 1];
        // queue代表的是即将要坐的bus
        List<Integer> startBus = stop2BusMap.get(source);
        for (int bus : startBus) {
            visitedBus[bus] = true;
            for (int stop : routes[bus]) {
                queue.add(stop);
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curStop = queue.poll();
                if (curStop == target) {
                    return count + 1;
                }
                List<Integer> nextBuses = stop2BusMap.get(curStop);
                for (int nextBus : nextBuses) {
                    if (visitedBus[nextBus]) {
                        continue;
                    }
                    visitedBus[nextBus] = true;
                    for (int nextStop : routes[nextBus]) {
                        queue.add(nextStop);
                    }
                }
            }
            count++;
        }
        return -1;

    }
}
