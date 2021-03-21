package 算法.搜索.深度优先搜索;

public class No547省份数量 {

    boolean[] visited;

    // 只需要最后的连通块，所以并查集当然也可以
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        visited = new boolean[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            dfs(isConnected, i);
            res++;
        }
        return res;
    }

    private void dfs(int[][] isConnected, int index) {
        int[] canReach = isConnected[index];
        // 不是从index开始，因为小的也是可能路径
        for (int i = 0; i < canReach.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (canReach[i] == 1) {
                visited[i] = true;
                dfs(isConnected, i);
            }
        }
    }
}
