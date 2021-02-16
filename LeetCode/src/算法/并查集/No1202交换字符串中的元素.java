package 算法.并查集;

import java.util.*;

public class No1202交换字符串中的元素 {

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        UnionFind unionFind = new UnionFind(s.length());
        char[] charArr = s.toCharArray();
        for (List<Integer> pair : pairs) {
            unionFind.union(pair.get(0), pair.get(1));
        }

        // 循环一遍并归一
        // Index-Children的Map
        Map<Integer, List<Integer>> group = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int parent = unionFind.find(i);
            group.computeIfAbsent(parent, key -> new ArrayList<>()).add(i);
        }

        char[] resCharArr = new char[s.length()];
        // 对于每一个Group,排序并重置
        for (List<Integer> groupIndex : group.values()) {
            List<Character> chars = new ArrayList<>();
            for (int index : groupIndex) {
                chars.add(charArr[index]);
            }
            // 排序
            Collections.sort(chars);
            // 插入
            for (int i = 0; i < groupIndex.size(); i++) {
                resCharArr[groupIndex.get(i)] = chars.get(i);
            }
        }
        return new String(resCharArr);
    }
}
