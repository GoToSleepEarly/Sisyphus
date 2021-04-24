package 数据结构.树.普通二叉树.前中后序遍历;


import basic.TreeNode;

public class 从先序遍历还原二叉树 {

    int index;

    public TreeNode recoverFromPreorder(String S) {
        index = 0;
        return helper(S, 0);
    }

    private TreeNode helper(String s, int depth) {
        // 核心在于如何知道index是否属于该节点，if so，属于左还是右。
        // 同样的，递归出口放在开头，防止代码混乱，这样的缺点是，会多遍历一次叶子节点，个人感觉无伤大雅。
        // 对于这道题，-的数量和depth决定了当前index属不属于该节点
        // 第一次出现属于左，第二次出现属于右，如果左为空，右不可能存在。

        // 不满足的情况:不属于depth层，即-的数量不满足
        for (int i = 0; i < depth; i++) {
            if (index + i >= s.length() || s.charAt(index + i) != '-') {
                return null;
            }
        }

        // 满足的话，获取数字的值
        int val = 0;
        index += depth;
        for (int i = index; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '-') {
                val = 10 * val + (c - '0');
                index++;
            } else {
                break;
            }
        }
        TreeNode cur = new TreeNode(val);

        cur.left = helper(s, depth + 1);
        if (cur.left != null) {
            cur.right = helper(s, depth + 1);
        }
        return cur;

    }
}
