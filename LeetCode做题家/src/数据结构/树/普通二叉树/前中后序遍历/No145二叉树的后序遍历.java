package 数据结构.树.普通二叉树.前中后序遍历;

import basic.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class No145二叉树的后序遍历 {
    public List<Integer> postorderTraversal(TreeNode root) {
        // 在中序遍历中，我们使用了自定义的Node
        // 实际上可以塞一个null表示当前节点的前一个节点可以输出
        List<Integer> res = new LinkedList<>();
        if (root == null) {

            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            // 开始访问结点
            if (cur != null) {
                stack.add(cur);
                stack.add(null);
                if (cur.right != null) {
                    stack.add(cur.right);
                }
                if (cur.left != null) {
                    stack.add(cur.left);
                }
            } else {
                res.add(stack.pop().val);
            }
        }
        return res;
    }

}
