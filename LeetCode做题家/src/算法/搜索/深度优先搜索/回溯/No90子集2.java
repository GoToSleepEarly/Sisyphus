package 算法.搜索.深度优先搜索.回溯;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class No90子集2 {

    List<List<Integer>> res;
    boolean[] visited;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new LinkedList<>();
        visited = new boolean[nums.length];
        // 需要去重
        Arrays.sort(nums);
        dfs(nums, 0, new LinkedList<>());
        return res;
    }

    private void dfs(int[] nums, int index, List<Integer> curPath) {
        if (index == nums.length) {
            res.add(new LinkedList<>(curPath));
            return;
        }
        // 选
        // 选+不选 == 不选+选，故择其一
        if (index <= 0 || nums[index] != nums[index - 1] || visited[index - 1]) {
            visited[index] = true;
            curPath.add(nums[index]);
            dfs(nums, index + 1, curPath);
            curPath.remove(curPath.size() - 1);
            visited[index] = false;
        }

        // 不选
        dfs(nums, index + 1, curPath);

    }

}
