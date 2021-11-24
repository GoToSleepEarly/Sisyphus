package 周赛;

import java.util.HashSet;
import java.util.Set;

public class 访问完所有房间的第一天 {

    public int firstDayBeenInAllRooms(int[] nextVisit) {
        boolean[] isOdd = new boolean[nextVisit.length];
        int cnt =0;
        int room = 0;
        Set<Integer> visited = new HashSet<>();
        while (true){
            visited.add(room);
            //算上本次访问
            if (isOdd[room]){
                isOdd[room] = false;
                room = nextVisit[room];
            }else {
                
            }
        }
    }
}
