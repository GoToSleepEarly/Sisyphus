package 数据结构.树.二叉搜索树;

import basic.TreeNode;

public class No530二叉搜索树的最小绝对差 {
    int pre;
    int res;

    public int getMinimumDifference(TreeNode root) {
        // 这题奇妙的点在于，普通递归很难做
        pre = Integer.MAX_VALUE;
        res = Integer.MAX_VALUE;
        preOrder(root);
        return res;
    }

    private void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        preOrder(root.left);
        if (pre != Integer.MAX_VALUE) {
            res = Math.min(root.val - pre, res);
        }
        pre = root.val;
        preOrder(root.right);
    }
}
