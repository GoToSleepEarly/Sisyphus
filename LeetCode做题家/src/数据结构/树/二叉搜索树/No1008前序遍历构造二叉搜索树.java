package 数据结构.树.二叉搜索树;

import basic.TreeNode;

public class No1008前序遍历构造二叉搜索树 {
    int index = 1;

    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        dfs(preorder, root, 0, Integer.MAX_VALUE);
        return root;
    }

    private void dfs(int[] preorder, TreeNode root, int minValue, int maxValue) {
        if (index == preorder.length) {
            return;
        }
        TreeNode cur = new TreeNode(preorder[index]);
        if (preorder[index-1] < root.val) {
            index++;
            dfs(preorder, cur, minValue, cur.val);
            root.left = cur;
        }
        if (preorder[index-1] > minValue && preorder[index-1] < maxValue) {
            index++;
            dfs(preorder, cur, cur.val, maxValue);
            root.right = cur;
        }
    }
}
