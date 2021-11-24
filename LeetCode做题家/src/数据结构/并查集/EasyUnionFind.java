package 数据结构.并查集;

public class EasyUnionFind {
    int[] parent;

    EasyUnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int index) {
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }

    void union(int indexA, int indexB) {
        int parentA = find(indexA);
        int parentB = find(indexB);
        // 挂在下面
        parent[parentB] = parentA;
    }

    void flat() {
        for (int i = 0; i < parent.length; i++) {
            parent[i] = find(i);
        }
    }

    int[] getParent() {
        return parent;
    }

}
