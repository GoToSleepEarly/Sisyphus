package 周赛;

public class 检查句子中的数字是否递增 {

    public boolean areNumbersAscending(String s) {
        String[] words = s.split(" ");
        int last = -1;
        for (String word : words) {
            if (!Character.isDigit(word.charAt(0))) {
                continue;
            }
            int x = Integer.parseInt(word);
            if (x <= last) {
                return false;
            }
            last = x;
        }
        return true;
    }
}
