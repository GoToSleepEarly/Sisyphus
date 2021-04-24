package 数据结构.图.连通性;

public class 冗余连接 {
    public int[] findRedundantConnection(int[][] edges) {
        UnionFind uf = new UnionFind(edges.length);
        // 并查集
        for (int[] edge : edges) {
            if (uf.find(edge[0]) == uf.find(edge[1])) {
                return edge;
            }

            uf.union(edge[0], edge[1]);
        }
        return null;
    }
}

class UnionFind {
    int[] parent;

    UnionFind(int n) {
        parent = new int[n + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    // 可以优化路径压缩
    public int find(int index) {
        while (parent[index] != index) {
            index = parent[index];
        }
        return index;
    }

    public void union(int i1, int i2) {
        int parent2 = find(i2);
        parent[parent2] = i1;
    }
}