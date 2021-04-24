package 数据结构.树.普通二叉树.递归应用;

import basic.TreeNode;

public class No124二叉树中的最大路径和 {

    int max;

    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 到左节点的最大值
        int left = dfs(root.left);
        // 到右节点的最大值
        int right = dfs(root.right);
        // 如果用到该节点
        int curMax = getMax(left, right, left + right, 0) + root.val;
        max = Math.max(max, curMax);
        return getMax(left + root.val, right + root.val, root.val);
    }

    private int getMax(int... left) {
        int max = Integer.MIN_VALUE;
        for (int num : left) {
            max = Math.max(num, max);
        }
        return max;
    }
}
