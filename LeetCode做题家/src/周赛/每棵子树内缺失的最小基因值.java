package 周赛;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 每棵子树内缺失的最小基因值 {
    public static void main(String[] args) {
        new 每棵子树内缺失的最小基因值().smallestMissingValueSubtree(
                new int[]{-1, 0, 0, 2},
                new int[]{1, 2, 3, 4}
        );
    }

    public static class TreeNode {
        TreeNode left;
        TreeNode right;
        int num;
        int index;

        TreeNode(int index, int num) {
            this.index = index;
            this.num = num;
        }
    }

    public static class UnionFind {
        int[] parent;
        int[] stop;

        UnionFind(int n) {
            parent = new int[n + 1];
            stop = new int[n + 1];
            Arrays.fill(parent, -1);
        }

        public int find(int index) {
            // 路径压缩
            // 循环：往上跳一级
            // 递归 parent[index] = find(parent[index]);
            while (parent[index] != stop[index]) {
                parent[index] = parent[parent[index]];
                index = parent[index];
            }
            return parent[index];
        }

        public boolean union(int i1, int i2) {
            parent[i1] = i2;
            return true;
        }
    }

    int[] res;
    UnionFind unionFind;

    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        res = new int[parents.length];
        unionFind = new UnionFind((int) Math.pow(10, 5));
        Map<Integer, TreeNode> treeNodeMap = new HashMap<>();
        TreeNode root = new TreeNode(0, nums[0]);
        unionFind.parent[nums[0]] = 0;
        treeNodeMap.put(0, root);
        for (int i = 1; i < parents.length; i++) {
            int parent = parents[i];
            int num = nums[i];
            unionFind.parent[num] = i;
            TreeNode cur = new TreeNode(i, num);
            TreeNode parentNode = treeNodeMap.get(parent);
            if (parentNode.left == null) {
                parentNode.left = cur;
            } else {
                parentNode.right = cur;
            }
            treeNodeMap.put(i, cur);
        }
        unionFind.stop = unionFind.parent.clone();
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        dfs(root.right);
        if (root.left != null) {
            unionFind.union(root.left.index, root.index);
        }
        if (root.right != null) {
            unionFind.union(root.right.num, root.index);
        }
        int[] parent = unionFind.parent;
        for (int i = 1; i < parent.length; i++) {
            if (unionFind.find(i) != root.index) {
                res[root.index] = i;
                break;
            }
        }
    }

}


