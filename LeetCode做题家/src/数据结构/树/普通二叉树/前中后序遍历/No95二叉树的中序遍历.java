package 数据结构.树.普通二叉树.前中后序遍历;

import basic.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class No95二叉树的中序遍历 {

    public List<Integer> inorderTraversal(TreeNode root) {
        Deque<InOrderNode> stack = new LinkedList<>();
        stack.addFirst(new InOrderNode(false, root));
        List<Integer> res = new LinkedList<>();
        while (!stack.isEmpty()) {
            InOrderNode cur = stack.pollFirst();
            if (cur.node == null) {
                continue;
            }
            if (cur.canPrint) {
                res.add(cur.node.val);
                continue;
            }
            stack.addFirst(new InOrderNode(false, cur.node.right));
            cur.canPrint = true;
            stack.addFirst(cur);
            stack.addFirst(new InOrderNode(false, cur.node.left));
        }
        return res;
    }
}

