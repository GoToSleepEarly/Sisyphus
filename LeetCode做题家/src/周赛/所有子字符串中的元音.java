package 周赛;

import java.util.*;

public class 所有子字符串中的元音 {

    public static void main(String[] args) {
        new 所有子字符串中的元音().countVowels("aba");
    }

    static Set<Character> set = new HashSet<>();

    static {
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
    }

    public long countVowels(String word) {
        int n = word.length();
        int[] cnt = new int[n];
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (set.contains(word.charAt(i))) {
                map.put(word.charAt(i), i);
            }
            cnt[i] = map.size();
        }
        int[] dp = new int[n];
        dp[0] = Arrays.stream(cnt).sum();
        for (int i = 1; i < n; i++) {
            char remove = word.charAt(i - 1);
            if (!set.contains(remove)) {
                dp[i] = dp[i - 1];
            } else if (map.get(remove) != i - 1) {
                dp[i] = dp[i - 1]-1;
            } else {
                dp[i] = dp[i - 1] - n - i;
            }
        }
        long res = 0;
        for (int i : dp) {
            res+=i;
        }
        return res;
    }
}
