package 数据结构.树.二叉搜索树;

import basic.TreeNode;

public class No450删除二叉搜索树中的节点 {
    public static void main(String[] args) {
    }

    /**
     * 非常好的错误示范，直接拼接法和标准答案法
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode dummy = new TreeNode(-1);
        dummy.left = root;
        helper(dummy, root, key);
        return dummy.left;
    }

    private void helper(TreeNode parent, TreeNode curNode, int key) {
        if (curNode == null) {
            return;
        }
        if (curNode.val == key) {
            // 左节点
            if (curNode == parent.left) {
                if (curNode.left == null && curNode.right == null) {
                    parent.left = null;
                } else if (curNode.left != null && curNode.right != null) {
                    // 都不为空
                    parent.left = curNode.left;
                    putToRightest(curNode.left, curNode.right);
                } else {
                    parent.left = curNode.left == null ? curNode.right : curNode.left;
                }
            }

            // 右节点
            if (curNode == parent.right) {
                if (curNode.left == null && curNode.right == null) {
                    parent.right = null;
                } else if (curNode.left != null && curNode.right != null) {
                    // 都不为空
                    parent.right = curNode.left;
                    putToRightest(curNode.left, curNode.right);
                } else {
                    parent.right = curNode.left == null ? curNode.right : curNode.left;
                }
            }

        } else if (curNode.val > key) {
            helper(curNode, curNode.left, key);
        } else {
            helper(curNode, curNode.right, key);
        }
    }

    private void putToRightest(TreeNode curNode, TreeNode right) {
        while (curNode.right != null) {
            curNode = curNode.right;
        }
        curNode.right = right;
    }
}
