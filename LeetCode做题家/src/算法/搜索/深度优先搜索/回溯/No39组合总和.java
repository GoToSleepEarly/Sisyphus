package 算法.搜索.深度优先搜索.回溯;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class No39组合总和 {

    List<List<Integer>> res;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new LinkedList<>();
        Arrays.sort(candidates);
        // 无重复元素，不需要visited数组
        dfs(candidates, target, 0, new LinkedList<>());
        return res;
    }

    private void dfs(int[] candidates, int target, int index, List<Integer> curPath) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new LinkedList<>(curPath));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            // 重点理解这里剪枝，前提是候选数组已经有序，
            if (target - candidates[i] < 0) {
                return;
            }
            curPath.add(candidates[i]);
            dfs(candidates, target - candidates[i], i, curPath);
            curPath.remove(curPath.size() - 1);
        }
    }
}
