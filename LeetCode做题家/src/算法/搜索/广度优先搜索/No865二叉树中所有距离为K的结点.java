package 算法.搜索.广度优先搜索;

import basic.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class No865二叉树中所有距离为K的结点 {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        // 额外记录父节点
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        parent.put(root, root);
        // bfs或者dfs寻找根都可以
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            TreeNode left = cur.left;
            TreeNode right = cur.right;

            if (left != null) {
                parent.put(left, cur);
                queue.add(left);
            }
            if (right != null) {
                parent.put(right, cur);
                queue.add(right);
            }
        }

        // BFS，第k次就是答案
        queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.add(target);
        visited.add(target);
        int count = 0;
        while (count != K) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                TreeNode left = cur.left;
                TreeNode right = cur.right;
                TreeNode par = parent.get(cur);
                putNext(queue, visited, left);
                putNext(queue, visited, right);
                putNext(queue, visited, par);
            }
            count++;
        }
        return queue.stream().map(i -> i.val).collect(Collectors.toList());

    }

    private void putNext(Queue<TreeNode> queue, Set<TreeNode> visited, TreeNode treeNode) {
        if (treeNode != null && !visited.contains(treeNode)) {
            queue.add(treeNode);
            visited.add(treeNode);
        }
    }
}
