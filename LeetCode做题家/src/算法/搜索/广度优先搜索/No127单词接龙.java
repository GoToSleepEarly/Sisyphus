package 算法.搜索.广度优先搜索;

import java.util.*;

public class No127单词接龙 {

    /**
     * beginWord = "hit",
     * endWord = "cog",
     * wordList = ["hot","dot","dog","lot","log","cog"]
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        Map<String, List<String>> nextMap = new HashMap<>(wordList.size() + 1, 1);
        for (int i = 0; i < wordList.size(); i++) {
            String from = wordList.get(i);
            for (int j = i + 1; j < wordList.size(); j++) {
                String to = wordList.get(j);
                // 判断是否能转换
                if (canTrans(from, to)) {
                    nextMap.computeIfAbsent(from, (k) -> new LinkedList<>()).add(to);
                    nextMap.computeIfAbsent(to, (k) -> new LinkedList<>()).add(from);
                }
            }
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int count = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                visited.add(cur);
                List<String> nexts = nextMap.get(cur);
                for (String next : nexts) {
                    if (next.equals(endWord)) {
                        return count+1;
                    }
                    if (visited.add(next)) {
                        queue.add(next);
                    }
                }
            }
            count++;
        }

        return 0;
    }

    private boolean canTrans(String from, String to) {
        int dif = 0;
        for (int i = 0; i < from.length(); i++) {
            if (from.charAt(i) != to.charAt(i)) {
                dif++;
                if (dif == 2) {
                    return false;
                }
            }
        }
        return true;
    }
}
