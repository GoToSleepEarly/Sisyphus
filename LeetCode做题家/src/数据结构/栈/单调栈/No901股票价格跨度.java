package 数据结构.栈.单调栈;

import java.util.Deque;
import java.util.LinkedList;

public class No901股票价格跨度 {

    class StockSpanner {

        int index;
        Deque<Stock> stack;

        public StockSpanner() {
            this.index = 0;
            this.stack = new LinkedList<>();
        }

        public int next(int price) {
            int curDay = index++;
            while (!stack.isEmpty() && stack.peekFirst().getPrice() <= price) {
                stack.pollFirst();
            }
            int largerDayIndex = stack.isEmpty() ? -1 : stack.getFirst().getIndex();
            stack.addFirst(new Stock(curDay, price));
            return curDay - largerDayIndex;
        }
    }

    class Stock {
        public Stock(int index, int price) {
            this.index = index;
            this.price = price;
        }

        public int getIndex() {
            return index;
        }

        int index;

        public int getPrice() {
            return price;
        }

        int price;
    }
}
