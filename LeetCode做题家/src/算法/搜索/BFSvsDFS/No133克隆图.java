package 算法.搜索.BFSvsDFS;

import java.util.*;

/**
 * 全量搜索，BFS和DFS都可，一般来说DFS更容易
 */
public class No133克隆图 {

    Map<Integer, Node> map;

    public Node cloneGraphBFS(Node node) {
        if (node == null) {
            return null;
        }
        map = new HashMap<>();
        Deque<Pair> deque = new LinkedList<>();
        Node root = new Node(node.val);
        deque.addLast(new Pair(node, root));
        map.put(root.val, root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                Pair curPair = deque.pollFirst();
                Node from = curPair.from;
                Node to = curPair.to;
                for (Node neighbor : from.neighbors) {
                    if (map.containsKey(neighbor.val)) {
                        to.neighbors.add(map.get(neighbor.val));
                    } else {
                        Node next = new Node(neighbor.val);
                        to.neighbors.add(next);
                        map.put(neighbor.val, next);
                        deque.addLast(new Pair(neighbor, next));
                    }
                }
            }
        }
        return root;
    }

    class Pair {
        Node from;
        Node to;

        public Pair(Node from, Node to) {
            this.from = from;
            this.to = to;
        }
    }

    public Node cloneGraphDFS(Node node) {
        map = new HashMap<>();
        if (node == null) {
            return null;
        }
        return dfs(node);
    }

    private Node dfs(Node node) {
        Node cur = new Node(node.val);
        map.put(cur.val, cur);
        for (Node next : node.neighbors) {
            if (map.containsKey(next.val)) {
                cur.neighbors.add(map.get(next.val));
            } else {
                cur.neighbors.add(dfs(next));
            }

        }
        return cur;
    }
}

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}