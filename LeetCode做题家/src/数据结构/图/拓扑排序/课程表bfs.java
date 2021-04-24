package 数据结构.图.拓扑排序;

import java.util.*;

public class 课程表bfs {

    // 正向BFS做法
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 构建图:key-课程n,value-前继课程
        Map<Integer, Set<Integer>> preMap = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            preMap.put(i, new HashSet<>());
        }
        for (int[] pre : prerequisites) {
            preMap.get(pre[0]).add(pre[1]);
        }
        boolean[] visited = new boolean[numCourses];
        // 统计入度为0的点
        Deque<Integer> toLearn = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (preMap.get(i).isEmpty()) {
                toLearn.add(i);
                visited[i] = true;
            }
        }

        // 开始bfs
        while (!toLearn.isEmpty()) {
            int size = toLearn.size();
            for (int i = 0; i < size; i++) {
                int course = toLearn.pollFirst();
                // 删除边
                for (int k = 0; k < numCourses; k++) {
                    Set<Integer> preSet = preMap.get(k);
                    preSet.remove(course);
                    if (!visited[k] && preSet.isEmpty()) {
                        toLearn.addLast(k);
                        visited[k] = true;
                    }
                }
            }
        }
        for (int i = 0; i < numCourses; i++) {
            if (preMap.get(i).size() != 0) {
                return false;
            }
        }
        return true;
    }
}
