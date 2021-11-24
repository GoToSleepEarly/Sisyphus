package 周赛;

public class 值相等的最小索引 {

    public int smallestEqual(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if(i % 10 ==nums[i]){
                return i;
            }
        }
        return -1;
    }
}
