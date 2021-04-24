package 数据结构.图.拓扑排序;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 课程表2dfs {

    int[] res;
    int index;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        res = new int[numCourses];
        index = numCourses - 1;
        Map<Integer, Set<Integer>> preMap = new HashMap<>();
        // 0未访问，1正在访问，2访问完毕
        int[] visited = new int[numCourses];
        // 构建图
        for (int i = 0; i < numCourses; i++) {
            preMap.put(i, new HashSet<>());
        }
        for (int[] pre : prerequisites) {
            preMap.get(pre[1]).add(pre[0]);
        }
        // 对每一个顶点dfs
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(preMap, i, visited)) {
                return new int[0];
            }
        }
        return res;
    }

    private boolean dfs(Map<Integer, Set<Integer>> preMap, int i, int[] visited) {
        // 有问题
        if (visited[i] == 1) {
            return false;
        }

        if (visited[i] == 2) {
            return true;
        }

        visited[i] = 1;
        Set<Integer> preSet = preMap.get(i);

        for (int pre : preSet) {
            if (!dfs(preMap, pre, visited)) {
                return false;
            }

        }

        visited[i] = 2;
        res[index--] = i;
        return true;
    }
}
