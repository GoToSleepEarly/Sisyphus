package 算法.搜索.深度优先搜索;

import basic.TreeNode;

public class No979在二叉树中分配硬币 {
    int res = 0;

    public int distributeCoins(TreeNode root) {
        // 奇怪的递归
        // 对于左右字数，各需要2个返回值，总个数，总金币数
        // 发现对于结果而言，总个数-总金币数就是需要移动的数量，所以简化为一个变量了~
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        // 需要操作
        res += Math.abs(left) + Math.abs(right);
        return left + right + root.val - 1;
    }
}
