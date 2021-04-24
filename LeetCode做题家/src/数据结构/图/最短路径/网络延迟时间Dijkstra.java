package 数据结构.图.最短路径;

import java.util.*;

public class 网络延迟时间Dijkstra {
    public static void main(String[] args) {
        网络延迟时间Dijkstra s = new 网络延迟时间Dijkstra();
        s.networkDelayTime(new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 2}}, 3, 1);
    }

    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> nextMap = new HashMap<>();
        // 构建图
        for (int[] edge : times) {
            nextMap.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
        }
        boolean[] visited = new boolean[N + 1];
        // 小的放前
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge[1]));
        // 保存距离
        int[] dis = new int[N + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[K] = 0;
        // 从K开始,初始距离为0
        queue.add(new int[]{K, 0});
        while (!queue.isEmpty()) {
            int[] curEdge = queue.poll();
            int curNode = curEdge[0];
            int curLen = curEdge[1];
            visited[curNode] = true;
            if (curLen > dis[curNode]) {
                continue;
            }
            dis[curNode] = curLen;
            for (int[] nextEdge : nextMap.getOrDefault(curNode, new ArrayList<>())) {
                if (!visited[nextEdge[0]]) {
                    // 下一个路径的长度
                    queue.add(new int[]{nextEdge[0], dis[curNode] + nextEdge[1]});
                }
            }
        }
        int max = 0;
        for (int i = 1; i < dis.length; i++) {
            if (i == K) {
                continue;
            }
            if (dis[i] == Integer.MAX_VALUE) {
                return -1;
            }
            max = Math.max(max, dis[i]);
        }
        return max;

    }
}
