package 数据结构.并查集;

public class UnionFind {
    int[] parent;
    int[] rank;

    UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int index) {
        // 路径压缩
        // 循环：往上跳一级
        // 递归 parent[index] = find(parent[index]);
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }

    public boolean union(int i1, int i2) {
        int parent1 = find(i1);
        int parent2 = find(i2);
        // 相同就不需要合并
        if (parent1 == parent2) {
            return false;
        }
        // 小的并入大的
        if (rank[parent1] < rank[parent2]) {
            parent[parent1] = parent2;
            rank[parent2] += rank[parent1];
        } else {
            parent[parent2] = parent1;
            rank[parent1] += rank[parent2];
        }
        return true;
    }
}