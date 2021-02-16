package 算法.动态规划;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class No413等差数列划分 {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        List<Integer> list = new LinkedList<>();
        for(int i =0;i<15;i++){
            list.add(i);
        }
        Set<String> set = new HashSet<>();
        list.parallelStream().forEach(i->{
            set.add(Thread.currentThread().getName());
            try {
                Thread.sleep(10*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(set.size());
    }

}
