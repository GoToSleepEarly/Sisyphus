package 算法.搜索.广度优先搜索;

import java.util.LinkedList;
import java.util.Queue;

public class No934最短的桥 {

    public static void main(String[] args) {
        No934最短的桥 s = new No934最短的桥();
        s.shortestBridge(new int[][]{{0, 1}, {1, 0}});
    }

    Queue<int[]> queue;
    boolean[][] visited;
    int[][] dirs;
    int m;
    int n;
    int[][] A;

    public int shortestBridge(int[][] A) {
        // 一遍dfs，一遍bfs
        this.A = A;
        m = A.length;
        n = A[0].length;
        dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        queue = new LinkedList<>();
        visited = new boolean[m][n];
        // 第一个岛的位置
        Loop:
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) {
                    dfs(i, j);
                    break Loop;
                }
            }
        }
        // 开始bfs，找寻1
        visited = new boolean[m][n];
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int[] dir : dirs) {
                    int nextI = cur[0] + dir[0];
                    int nextY = cur[1] + dir[1];
                    if (!isValidIndex(nextI, nextY) || visited[nextI][nextY]) {
                        continue;
                    }
                    if (A[nextI][nextY] == 1) {
                        return count;
                    }
                    if (A[nextI][nextY] == 0) {
                        queue.add(new int[]{nextI, nextY});
                        visited[nextI][nextY] = true;
                    }
                }
            }
            count++;
        }
        return -1;

    }

    private void dfs(int i, int j) {
        queue.add(new int[]{i, j});
        // 改为第二座岛
        A[i][j] = 2;
        visited[i][j] = true;
        for (int[] dir : dirs) {
            int nextI = i + dir[0];
            int nextJ = j + dir[1];
            if (!isValidIndex(nextI, nextJ) || visited[nextI][nextJ]) {
                continue;
            }
            if (A[nextI][nextJ] == 1) {
                dfs(nextI, nextJ);
            }
        }
    }

    private boolean isValidIndex(int i, int j) {
        return i >= 0 && i < m
                && j >= 0 && j < n;
    }

}
