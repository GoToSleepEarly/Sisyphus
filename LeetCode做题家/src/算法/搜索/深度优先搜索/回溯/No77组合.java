package 算法.搜索.深度优先搜索.回溯;

import java.util.LinkedList;
import java.util.List;

public class No77组合 {
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        dfs(n, 1, k, 0, new LinkedList<>());
        return res;
    }

    private void dfs(int n, int count, int k, int start, LinkedList<Integer> curPath) {
        if (count == k + 1) {
            res.add(new LinkedList<>(curPath));
            return;
        }

        // 每次选择一个
        for (int i = start + 1; i <= n; i++) {
            curPath.add(i);
            dfs(n, count + 1, k, i, curPath);
            curPath.remove(curPath.size() - 1);
        }
    }
}
