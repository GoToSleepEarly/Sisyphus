package 算法.搜索.深度优先搜索.回溯;

public class No60排列序列 {

    boolean[] visited;
    StringBuilder sb;

    public String getPermutation(int n, int k) {
        visited = new boolean[n + 1];
        sb = new StringBuilder();
        dfs(n, n, k);
        return sb.toString();
    }

    private void dfs(int n, int remainder, int k) {
        if (remainder == 0) {
            return;
        }
        int each = getEachNum(remainder);
        int treeIndex = (k / each) + 1;
        treeIndex = k % each == 0 ? treeIndex - 1 : treeIndex;
        int nextK = k % each;
        nextK = nextK == 0 ? each : nextK;
        int index = 1;
        int count = 0;
        // 获取第treeIndex个没被访问过的数
        while (count != treeIndex) {
            if (!visited[index]) {
                count++;
            }
            index++;
        }
        index--;
        visited[index] = true;
        sb.append(index);
        dfs(n, remainder - 1, nextK);
    }

    private int getEachNum(int size) {
        int res = 1;
        for (int i = 1; i < size; i++) {
            res *= i;
        }
        return res;
    }
}
