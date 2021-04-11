package 算法.搜索.深度优先搜索;

import java.util.*;

public class No332重新安排行程 {

    public List<String> findItinerary(List<List<String>> tickets) {
        // 因为逆序插入，所以用链表
        List<String> ans = new LinkedList<>();
        if (tickets == null || tickets.size() == 0)
            return ans;
        Map<String, TreeMap<String, Integer>> graph = new HashMap<>();
        for (List<String> pair : tickets) {
            // 因为涉及删除操作，我们用链表
            TreeMap<String, Integer> cur = graph.computeIfAbsent(pair.get(0), k -> new TreeMap<>());
            cur.put(pair.get(1), cur.getOrDefault(pair.get(1), 0) + 1);
        }
        // 按目的顶点排序
        // 回溯法
        ans.add("JFK");
        dfs(graph, "JFK", ans, tickets.size() + 1);
        return ans;
    }

    private boolean dfs(Map<String, TreeMap<String, Integer>> graph, String from, List<String> ans, int count) {
        if (count == ans.size()) {
            return true;
        }
        if (!graph.containsKey(from)) {
            return false;
        }
        TreeMap<String, Integer> nexts = graph.get(from);
        for (Map.Entry<String, Integer> entry : nexts.entrySet()) {
            if (entry.getValue() == 0) {
                continue;
            }
            // 路径减一
            ans.add(entry.getKey());
            nexts.put(entry.getKey(), entry.getValue() - 1);
            if (dfs(graph, entry.getKey(), ans, count)) {
                return true;
            }
            nexts.put(entry.getKey(), entry.getValue() + 1);
            ans.remove(ans.size() - 1);
        }
        return false;
    }

}
