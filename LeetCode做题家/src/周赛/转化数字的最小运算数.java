package 周赛;

import java.util.Deque;
import java.util.LinkedList;

public class 转化数字的最小运算数 {
    public int minimumOperations(int[] nums, int start, int goal) {
        // BFS
        Deque<Integer> queue = new LinkedList<>();
        int cnt = 0;
        queue.addLast(start);
        boolean[] visited = new boolean[1000 + 1];
        visited[start] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curNum = queue.pollFirst();
                for (int num : nums) {
                    int nextNum = curNum + num;
                    if (nextNum == goal) {
                        return cnt + 1;
                    }
                    if (nextNum >= 0 && nextNum <= 1000 && !visited[nextNum]) {
                        visited[nextNum] = true;
                        queue.addLast(nextNum);
                    }
                    nextNum = curNum - num;
                    if (nextNum == goal) {
                        return cnt + 1;
                    }
                    if (nextNum >= 0 && nextNum <= 1000 && !visited[nextNum]) {
                        visited[nextNum] = true;
                        queue.addLast(nextNum);
                    }
                    nextNum = curNum ^ num;
                    if (nextNum == goal) {
                        return cnt + 1;
                    }
                    if (nextNum >= 0 && nextNum <= 1000 && !visited[nextNum]) {
                        visited[nextNum] = true;
                        queue.addLast(nextNum);
                    }
                }
            }
            cnt++;
        }
        return -1;
    }
}
