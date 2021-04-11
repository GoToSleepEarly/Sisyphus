package 算法.搜索.回溯;

import java.util.LinkedList;
import java.util.List;

public class No131分割回文串回溯 {

    List<List<String>> res = new LinkedList<>();

    public List<List<String>> partition(String s) {
        // 使用回溯可以避免每一层都存储临时结果
        dfs(s.toCharArray(), 0, new LinkedList<>());
        return res;
    }

    private void dfs(char[] chars, int index, LinkedList<String> curPath) {

        if (index == chars.length) {
            res.add(new LinkedList<>(curPath));
            return;
        }
        for (int i = index; i < chars.length; i++) {
            if (checkPalindrome(chars, index, i)) {
                curPath.add(new String(chars, index, i - index + 1));
                dfs(chars, i + 1, curPath);
                curPath.remove(curPath.size() - 1);
            }
        }
    }

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
