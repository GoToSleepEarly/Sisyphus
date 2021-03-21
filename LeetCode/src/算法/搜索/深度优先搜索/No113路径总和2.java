package 算法.搜索.深度优先搜索;

import basic.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class No113路径总和2 {

    // 看到路径相关，回溯起飞
    List<List<Integer>> res;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return new LinkedList<>();
        }
        //经典回溯啊
        res = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        path.add(root.val);
        dfs(root, targetSum - root.val, path);
        return res;
    }

    private void dfs(TreeNode root, int curSum, List<Integer> curPath) {
        // 没说是正整数
        if (curSum == 0 && root.left == null && root.right == null) {
            res.add(new LinkedList<>(curPath));
            return;
        }
        // 左右递归
        if (root.left != null) {
            curPath.add(root.left.val);
            dfs(root.left, curSum - root.left.val, curPath);
            curPath.remove(curPath.size() - 1);
        }
        // 左右递归
        if (root.right != null) {
            curPath.add(root.right.val);
            dfs(root.right, curSum - root.right.val, curPath);
            curPath.remove(curPath.size() - 1);
        }
    }

}
