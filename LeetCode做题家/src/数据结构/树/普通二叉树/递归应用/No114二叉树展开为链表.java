package 数据结构.树.普通二叉树.递归应用;

import basic.TreeNode;

public class No114二叉树展开为链表 {

    TreeNode last;

    public void flatten(TreeNode root) {
        last = new TreeNode(-1);
        dfs(root);
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode right = root.right;
        last.right = root;
        last = root;
        dfs(root.left);
        root.left = null;
        dfs(right);
    }
}
