package 算法.双指针.滑动窗口;

public class No567字符串的排列 {

    public boolean checkInclusion(String s1, String s2) {
        int left = 0;
        int right = 0;
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] count1 = new int[26];
        for (char c : s1.toCharArray()) {
            count1[c - 'a']++;
        }
        int[] count2 = new int[26];
        int dif = s1.length();
        while (right < s2.length()) {
            // 添加right
            int add = s2.charAt(right) - 'a';
            right++;
            count2[add]++;
            // 需要正好满足才可以
            if (count2[add] == count1[add]) {
                dif -= count1[add];
            } else if (count2[add] == count1[add] + 1) {
                dif += count1[add];
            }
            // 需要收缩左侧
            if (right - left == s1.length() + 1) {
                int remove = s2.charAt(left) - 'a';
                count2[remove]--;
                if (count2[remove] == count1[remove]) {
                    dif -= count1[remove];
                } else if (count2[remove] == count1[remove] - 1) {
                    dif += count1[remove];
                }
                left++;
            }
            if (dif == 0) {
                return true;
            }
        }
        return false;
    }
}
