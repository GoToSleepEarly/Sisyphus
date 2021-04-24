package 数据结构.树.普通二叉树.前中后序遍历;

import basic.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class No144二叉树的前序遍历 {
    // 此处只用栈来实现
    public List<Integer> preorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        stack.addFirst(root);
        List<Integer> res = new LinkedList<>();
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollFirst();
            if (cur == null) {
                continue;
            }
            res.add(cur.val);
            stack.addFirst(cur.right);
            stack.addFirst(cur.left);
        }
        return res;
    }
}
