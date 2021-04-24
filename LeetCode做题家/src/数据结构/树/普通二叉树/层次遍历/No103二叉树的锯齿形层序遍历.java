package 数据结构.树.普通二叉树.层次遍历;

import basic.TreeNode;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class No103二叉树的锯齿形层序遍历 {


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return res;
        }
        queue.addLast(root);
        boolean order = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curLevel = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.pollFirst();
                curLevel.add(cur.val);
                if (cur.left != null)
                    queue.addLast(cur.left);
                if (cur.right != null)
                    queue.addLast(cur.right);
            }
            if (!order) {
                Collections.reverse(curLevel);
            }
            res.add(curLevel);
            order = !order;
        }
        return res;
    }
}
