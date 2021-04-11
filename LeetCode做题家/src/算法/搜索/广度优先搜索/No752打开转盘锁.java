package 算法.搜索.广度优先搜索;

import java.util.*;

public class No752打开转盘锁 {

    public int openLock1(String[] deadends, String target) {
        Deque<String> deque = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        HashSet<String> deadSets = new HashSet<>(Arrays.asList(deadends));
        deque.add("0000");
        visited.add("0000");
        if (deadSets.contains("0000")) {
            return -1;
        }
        int count = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                String cur = deque.poll();
                if (cur.equals(target)) {
                    return count;
                }
                // 4位依次进行变化
                for (int j = 0; j < 4; j++) {
                    String up = upOne(cur, j);
                    String down = downOne(cur, j);
                    //如果没遍历过且不在黑名单中，就可以继续
                    if (!deadSets.contains(up) && !visited.contains(up)) {
                        deque.add(up);
                        visited.add(up);
                    }
                    if (!deadSets.contains(down) && !visited.contains(down)) {
                        deque.add(down);
                        visited.add(down);
                    }
                }
            }
            count++;
        }
        return -1;
    }

    public int openLock(String[] deadends, String target) {
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        Set<String> now = new HashSet<>();
        Set<String> next = new HashSet<>();
        int count = 0;
        now.add("0000");
        next.add(target);
        if (visited.contains("0000")) {
            return -1;
        }
        while (!now.isEmpty() && !next.isEmpty()) {
            // 因为HashSet不能保证顺序，所以无法用Size确定每次的大小
            // 改用临时的HashSet存储结果
            Set<String> tmp = new HashSet<>();
            for (String cur : now) {
                if (next.contains(cur)) {
                    return count;
                }
                visited.add(cur);
                // 4位依次进行变化
                for (int j = 0; j < 4; j++) {
                    String up = upOne(cur, j);
                    String down = downOne(cur, j);
                    //如果没遍历过且不在黑名单中，就可以继续
                    if (!visited.contains(up)) {
                        tmp.add(up);
                    }
                    if (!visited.contains(down)) {
                        tmp.add(down);
                    }
                }
            }
            count++;
            if (now.size() > next.size()) {
                now = next;
                next = tmp;
            } else {
                now = tmp;
            }
        }
        return -1;
    }

    private String upOne(String cur, int j) {
        char[] chars = cur.toCharArray();
        chars[j] = chars[j] == '9' ? '0' : (char) (chars[j] + 1);
        return String.valueOf(chars);
    }

    private String downOne(String cur, int j) {
        char[] chars = cur.toCharArray();
        chars[j] = chars[j] == '0' ? '9' : (char) (chars[j] - 1);
        return String.valueOf(chars);
    }


}
