package 数据结构.图.最短路径;

import java.util.*;

public class 网络延迟时间SPFA {
    // SPFA：用邻接表写
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        // 构建邻接表
        for (int[] arr : times) {
            List<int[]> list = map.getOrDefault(arr[0], new ArrayList<>());
            list.add(new int[]{arr[1], arr[2]});
            map.put(arr[0], list);
        }
        // 初始化dis数组和vis数组
        int[] dis = new int[N + 1];
        int INF = 0x3f3f3f3f;
        Arrays.fill(dis, INF);
        boolean[] vis = new boolean[N + 1];
        dis[K] = dis[0] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(K);

        while (!queue.isEmpty()) {
            // 取出队首节点
            Integer poll = queue.poll();
            // 可以重复入队
            vis[poll] = false;
            // 遍历起点的邻居,更新距离
            List<int[]> list = map.getOrDefault(poll, Collections.emptyList());
            for (int[] arr : list) {
                int next = arr[0];
                // 如果没更新过，或者需要更新距离()
                if (dis[next] == INF || dis[next] > dis[poll] + arr[1]) {
                    // 更新距离
                    dis[next] = dis[poll] + arr[1];
                    // 如果队列中没有，就不需要再次入队了 （那么判断入度可以在这里做文章）
                    if (!vis[next]) {
                        vis[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }
        int res = Arrays.stream(dis).max().getAsInt();
        return res == INF ? -1 : res;
    }

}
