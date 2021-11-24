package 算法.搜索.深度优先搜索.回溯;

import java.util.ArrayList;
import java.util.List;

public class No131分割回文串递归法 {

    public List<List<String>> partition(String s) {
        return dfs(s.toCharArray(), 0);
    }

    private List<List<String>> dfs(char[] s, int start) {
        if (start == s.length) {
            List<String> sub = new ArrayList<>();
            List<List<String>> res = new ArrayList<>();
            res.add(sub);
            return res;
        }
        List<List<String>> res = new ArrayList<>();
        for (int i = start; i < s.length; i++) {
            if (checkPalindrome(s, start, i)) {
                String curPre = new String(s, start, i - start + 1);
                List<List<String>> subLists = dfs(s, i + 1);
                for (List<String> subList : subLists) {
                    subList.add(0, curPre);
                    res.add(subList);
                }
            }
        }
        return res;
    }

    /**
     * 这一步的时间复杂度是 O(N)，优化的解法是，先采用动态规划，把回文子串的结果记录在一个表格里
     *
     * @param charArray
     * @param left      子串的左边界，可以取到
     * @param right     子串的右边界，可以取到
     * @return
     */
    private boolean checkPalindrome(char[] charArray, int left, int right) {
        while (left < right) {
            if (charArray[left] != charArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


}
