package 算法.搜索.深度优先搜索.回溯;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class No47全排列2 {

    List<List<Integer>> res = new LinkedList<>();
    boolean[] visited;

    public List<List<Integer>> permuteUnique(int[] nums) {
        visited = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums, 0, new LinkedList<>());
        return res;
    }

    private void dfs(int[] nums, int count, LinkedList<Integer> curPath) {
        if (count == nums.length) {
            res.add(new LinkedList<>(curPath));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            curPath.add(nums[i]);
            dfs(nums, count + 1, curPath);
            visited[i] = false;
            curPath.remove(curPath.size() - 1);
        }
    }
}
