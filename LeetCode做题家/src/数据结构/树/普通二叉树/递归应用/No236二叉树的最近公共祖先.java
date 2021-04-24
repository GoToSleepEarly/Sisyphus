package 数据结构.树.普通二叉树.递归应用;

import basic.TreeNode;

public class No236二叉树的最近公共祖先 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 优先考虑递归
        // 转换思路：变成寻找一个节点，左子树右子树包含p,q
        Node ans = dfs(root, p, q);
        return ans.res;
    }

    private Node dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return new Node(0, null);
        }
        Node left = dfs(root.left, p, q);
        if (left.type == 2) {
            return left;
        }
        Node right = dfs(root.right, p, q);
        if (right.type == 2) {
            return right;
        }
        if (left.type == 1 && right.type == 1) {
            return new Node(2, root);
        }
        if (left.type == 1 || right.type == 1) {
            if (root.val == p.val || root.val == q.val) {
                return new Node(2, root);
            } else {
                return new Node(1, null);
            }
        }
        if (root.val == p.val || root.val == q.val) {
            return new Node(1, null);
        } else {
            return new Node(0, null);
        }

    }
}

class Node {
    public Node(int type, TreeNode res) {
        this.type = type;
        this.res = res;
    }

    int type;
    TreeNode res;

}
