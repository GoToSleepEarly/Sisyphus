package 数据结构.图.连通性;

import java.util.Deque;
import java.util.LinkedList;

public class 判断二分图 {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] union = new int[n];
        Deque<Integer> deque = new LinkedList<>();
        // 添加下一组

        for (int i = 0; i < graph.length; i++) {
            if (union[i] == 0 && graph[i].length != 0) {
                deque.addFirst(i);
                break;
            }
        }

        while (!deque.isEmpty()) {
            int cur = deque.pollFirst();
            int val = union[cur] == 1 ? 2 : 1;
            int[] next = graph[cur];

            for (int i = 0; i < next.length; i++) {
                if (union[next[i]] == 0) {
                    union[next[i]] = val;
                    deque.addLast(next[i]);
                } else if (union[next[i]] != val) {
                    return false;
                }
            }
            // 添加下一组
            if (deque.isEmpty()) {
                for (int i = 0; i < graph.length; i++) {
                    if (union[i] == 0 && graph[i].length != 0) {
                        deque.addFirst(i);
                        break;
                    }
                }
            }
        }

        return true;
    }
}
