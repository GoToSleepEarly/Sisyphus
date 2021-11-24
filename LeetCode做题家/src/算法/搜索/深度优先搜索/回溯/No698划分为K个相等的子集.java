package 算法.搜索.深度优先搜索.回溯;

import java.util.Arrays;

public class No698划分为K个相等的子集 {
    public boolean canPartitionKSubsets(int[] nums, int k) {

        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;
        int target = sum / k;
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        return dfs(nums, k, target, 0, 0, visited);
    }

    private boolean dfs(int[] nums, int k, int target, int cur, int index, boolean[] visited) {
        if (k == 0) {
            return true;
        }
        if (cur == target) {
            return dfs(nums, k - 1, target, 0, 0, visited);
        }
        if (index == nums.length) {
            return false;
        }

        // 都不满足，证明在凑target=k
        for (int i = index; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            // 排序过后，不需要考虑后面
            if (cur + nums[i] > target) {
                return false;
            }

            visited[i] = true;
            if (dfs(nums, k, target, cur + nums[i], i + 1, visited)) {
                return true;
            }
            visited[i] = false;

        }
        return false;
    }
}
