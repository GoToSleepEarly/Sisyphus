package 周赛;

public class 检查字符串是否为数组前缀 {

    public boolean isPrefixString(String s, String[] words) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length && sb.length() < s.length(); i++) {
            sb.append(words[i]);
        }
        return sb.toString().equals(s);
    }
}
