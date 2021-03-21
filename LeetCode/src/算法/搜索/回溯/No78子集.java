package 算法.搜索.回溯;

import java.util.LinkedList;
import java.util.List;

public class No78子集 {

    List<List<Integer>> res;

    public List<List<Integer>> subsets(int[] nums) {
        res = new LinkedList<>();
        dfs(nums, 0, new LinkedList<>());
        return res;
    }

    private void dfs(int[] nums, int index, List<Integer> curPath) {
        if (index == nums.length) {
            res.add(new LinkedList<>(curPath));
            return;
        }
        //选
        curPath.add(nums[index]);
        dfs(nums, index + 1, curPath);
        curPath.remove(curPath.size() - 1);

        // 不选
        dfs(nums, index + 1, curPath);

    }
}
