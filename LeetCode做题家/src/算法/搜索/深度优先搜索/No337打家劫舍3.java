package 算法.搜索.深度优先搜索;

import basic.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class No337打家劫舍3 {

    //记忆化递归
    Map<TreeNode, Integer> rob;
    Map<TreeNode, Integer> notRob;

    public int rob(TreeNode root) {
        rob = new HashMap<>();
        notRob = new HashMap<>();
        int robRoot = dfs(root, true);
        int notRobRoot = dfs(root, false);
        return Math.max(robRoot, notRobRoot);
    }

    private int dfs(TreeNode node, boolean isRob) {
        if (node == null) {
            return 0;
        }
        // 节点偷
        int cur;
        if (isRob) {
            if (rob.containsKey(node)) {
                return rob.get(node);
            }
            cur = dfs(node.left, false) + dfs(node.right, false) + node.val;
            rob.put(node, cur);
        } else {
            if (notRob.containsKey(node)) {
                return notRob.get(node);
            }
            cur = Math.max(dfs(node.left, true), dfs(node.left, false)) +
                    Math.max(dfs(node.right, true), dfs(node.right, false));
            notRob.put(node, cur);
        }
        return cur;
    }

}
