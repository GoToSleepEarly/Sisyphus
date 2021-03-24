package 算法.双指针.前缀和;

import basic.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class No437路径总和3 {
    Map<Integer, Integer> preSum;
    int res;

    public int pathSum(TreeNode root, int sum) {
        preSum = new HashMap<>();
        preSum.put(0, 1);
        res = 0;
        helper(root, sum, 0);
        return res;
    }

    private void helper(TreeNode root, int sum, int curSum) {
        if (root == null) {
            return;
        }

        int val = root.val;
        curSum += val;
        res += preSum.getOrDefault(curSum - sum, 0);
        preSum.put(curSum, preSum.getOrDefault(curSum, 0) + 1);
        helper(root.left, sum, curSum);
        helper(root.right, sum, curSum);
        preSum.put(curSum, preSum.get(curSum) - 1);
    }
}
