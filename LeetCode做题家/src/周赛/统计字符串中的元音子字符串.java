package 周赛;

import java.util.HashSet;
import java.util.Set;

public class 统计字符串中的元音子字符串 {

    static Set<Character> set = new HashSet<>();

    static {
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
    }

    public int countVowelSubstrings(String word) {
        //暴力一下吧
        int res = 0;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            Set<Character> exist = new HashSet<>();
            for (int j = i; j < n; j++) {
                if (!set.contains(word.charAt(j))) {
                    break;
                }
                exist.add(word.charAt(j));
                if (exist.size() == 5) {
                    res++;
                }
            }
            exist.clear();
        }
        return res;
    }

}
