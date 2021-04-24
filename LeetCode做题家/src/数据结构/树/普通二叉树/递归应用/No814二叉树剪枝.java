package 数据结构.树.普通二叉树.递归应用;

import basic.TreeNode;

public class No814二叉树剪枝 {

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val==0) {
            return null;
        }
        return root;
    }
}
