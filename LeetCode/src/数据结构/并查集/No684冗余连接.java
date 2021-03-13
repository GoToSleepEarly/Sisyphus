package 数据结构.并查集;

public class No684冗余连接 {

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        // 下标从1开始
        UnionFind unionFind = new UnionFind(n + 1);
        for (int[] edge : edges) {
            int e1 = edge[0];
            int e2 = edge[1];
            if (unionFind.find(e1) == unionFind.find(e2)) {
                return edge;
            }
            // 会有重复计算
            unionFind.union(e1, e2);
        }
        return new int[]{};
    }
}
