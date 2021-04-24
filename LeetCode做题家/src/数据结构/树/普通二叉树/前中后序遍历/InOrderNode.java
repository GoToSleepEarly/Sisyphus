package 数据结构.树.普通二叉树.前中后序遍历;

import basic.TreeNode;

public class InOrderNode {
    public boolean canPrint;
    public TreeNode node;

    public InOrderNode(boolean canPrint, TreeNode node) {
        this.canPrint = canPrint;
        this.node = node;
    }
}
