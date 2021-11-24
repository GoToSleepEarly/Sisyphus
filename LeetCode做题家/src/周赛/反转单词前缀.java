package 周赛;

public class 反转单词前缀 {

    public String reversePrefix(String word, char ch) {
        int i = word.indexOf(ch);
        if(i == -1){
            return word;
        }
        StringBuilder sb = new StringBuilder(word.substring(0,i));
        sb.reverse().append(word.substring(i+1));
        return sb.toString();
    }
}
