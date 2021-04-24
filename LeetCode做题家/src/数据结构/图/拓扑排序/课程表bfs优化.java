package 数据结构.图.拓扑排序;

import java.util.*;

public class 课程表bfs优化 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 正向传播,所以需要nextMap
        Map<Integer, Set<Integer>> nextMap = new HashMap<>();
        // 图默认值
        for (int i = 0; i < numCourses; i++) {
            nextMap.put(i, new HashSet<>());
        }
        // 对于每个结点，需要计算入度，当入度为0时开始bfs
        int[] inDegrees = new int[numCourses];
        // 初始化
        for (int[] pre : prerequisites) {
            nextMap.get(pre[1]).add(pre[0]);
            inDegrees[pre[0]]++;
        }
        //bfs队列
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.addLast(i);
            }
        }
        // 学习完成的课程
        int count = 0;
        // 开始bfs
        while (!queue.isEmpty()) {

            int learned = queue.pollFirst();
            count++;
            for (int next : nextMap.get(learned)) {
                inDegrees[next]--;
                if (inDegrees[next] == 0) {
                    queue.addLast(next);
                }
            }

        }

        return count == numCourses;
    }
}
