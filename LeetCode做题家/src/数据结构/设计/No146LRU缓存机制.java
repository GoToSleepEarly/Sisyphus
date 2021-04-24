package 数据结构.设计;

import java.util.HashMap;
import java.util.Map;

public class No146LRU缓存机制 {
    class LRUCache {

        private Map<Integer, Node> map;
        private DoubleList doubleList;
        private int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>(capacity);
            doubleList = new DoubleList();
        }

        public int get(int key) {
            if (!map.containsKey(key))
                return -1;
            else {
                Node node = map.get(key);
                doubleList.moveToFirst(node);
                return node.val;
            }
        }

        public void put(int key, int value) {
            Node node = new Node(key, value);
            if (map.containsKey(key)) {
                doubleList.remove(map.get(key));
            } else {
                if (doubleList.size() == capacity) {
                    map.remove(doubleList.removeLast().key);
                }
            }
            doubleList.addFirst(node);
            map.put(key, node);
        }
    }
    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    class DoubleList {

        private Node head;
        private Node tail;
        private int count = 0;

        public DoubleList() {
            head = new Node();
            tail = new Node();
            head.pre = null;
            head.next = tail;
            tail.pre = head;
            tail.next = null;
        }

        public void addFirst(Node node) {
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
            count++;
        }

        public void remove(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            count--;
        }

        public Node removeLast() {
            Node node = tail.pre;
            tail.pre = tail.pre.pre;
            tail.pre.next = tail;
            count--;

            return node;
        }

        public void moveToFirst(Node node) {
            remove(node);
            addFirst(node);
        }

        public int size() {
            return count;
        }
    }

    class Node {

        public int key, val;
        public Node next, pre;

        public Node() {

        }

        public Node(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }
}
