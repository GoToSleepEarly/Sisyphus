package 数据结构.图.欧拉回路;

import java.util.*;

public class No332重新安排行程 {

    public static void main(String[] args) {
        No332重新安排行程 s = new No332重新安排行程();
        List<List<String>> list = new LinkedList<>();
        list.add(List.of("JFK", "KUL"));
        list.add(List.of("JFK", "NRT"));
        list.add(List.of("NRT", "JFK"));
        s.findItinerary(list);
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        // 因为逆序插入，所以用链表
        List<String> ans = new LinkedList<>();
        if (tickets == null || tickets.size() == 0)
            return ans;
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> pair : tickets) {
            // 因为涉及删除操作，我们用链表
            graph.computeIfAbsent(pair.get(0), k -> new LinkedList<>()).add(pair.get(1));
        }
        // 按目的顶点排序
        graph.values().forEach(Collections::sort);
        visit(graph, "JFK", ans);
        return ans;
    }

    // DFS方式遍历图，当走到不能走为止，再将节点加入到答案
    private void visit(Map<String, List<String>> graph, String src, List<String> ans) {
        List<String> nbr = graph.get(src);
        while (nbr != null && nbr.size() > 0) {
            String dest = nbr.remove(0);
            visit(graph, dest, ans);
        }
        ans.add(0, src); // 逆序插入
    }
}
