package 周赛;

public class 作为子字符串出现在单词中的字符串数目 {

    public int numOfStrings(String[] patterns, String word) {
        int res=0;
        for (String pattern : patterns) {
            if(word.contains(pattern)){
                res++;
            }
        }
        return res;
    }
}
