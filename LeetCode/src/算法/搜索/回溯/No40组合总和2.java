package 算法.搜索.回溯;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class No40组合总和2 {

    List<List<Integer>> res;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new LinkedList<>();
        // 只能使用一次，反倒更简单了
        Arrays.sort(candidates);
        dfs(candidates, target, 0, new LinkedList<>());
        return res;
    }

    private void dfs(int[] candidates, int target, int index, LinkedList<Integer> curPath) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new LinkedList<>(curPath));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            //同一层的不能继续取
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }

            if (target - candidates[i] < 0) {
                return;
            }

            curPath.add(candidates[i]);
            dfs(candidates, target - candidates[i], i + 1, curPath);
            curPath.remove(curPath.size() - 1);
        }
    }

}
