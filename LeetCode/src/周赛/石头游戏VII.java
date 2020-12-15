package 周赛;

public class 石头游戏VII {
  
    public int stoneGameVII(int[] stones) {
        int alice = 0;
        int bob = 0;
        int sum = 0;
        for(int stone:stones){
            sum+=stone;
        }
        int left = 0;
        int right = stones.length-1;
        boolean isAlice = true;
        while(left <=right){
            // 拿左边
            int curMin;
            if(stones[left] < stones[right]){

            }
            if(isAlice){

            }
        }
    }
}
