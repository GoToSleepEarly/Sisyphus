package 算法.搜索.深度优先搜索.回溯;

import java.util.LinkedList;
import java.util.List;

public class No46全排列 {

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        dfs(nums, 0, new LinkedList<>());
        return res;
    }

    private void dfs(int[] nums, int index, List<Integer> curPath) {
        if (index == nums.length) {
            res.add(new LinkedList<>(curPath));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            // 选择第一位，并继续回溯
            swap(nums, index, i);
            curPath.add(nums[index]);
            dfs(nums, index + 1, curPath);
            // 去除选择
            swap(nums, index, i);
            curPath.remove(curPath.size() - 1);
        }

    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
