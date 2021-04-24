package 数据结构.树.普通二叉树.递归应用;

import basic.TreeNode;

public class No543二叉树的直径 {

    int max;

    public int diameterOfBinaryTree(TreeNode root) {
        max = Integer.MIN_VALUE;
        dfs(root);
        return max - 1;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        max = Math.max(left + right + 1, max);
        return Math.max(left, right) + 1;
    }
}
