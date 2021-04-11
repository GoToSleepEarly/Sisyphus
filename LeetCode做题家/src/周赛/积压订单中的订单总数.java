package 周赛;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 积压订单中的订单总数 {

    public static void main(String[] args) {
        积压订单中的订单总数 s = new 积压订单中的订单总数();
        s.getNumberOfBacklogOrders(new int[][]{{7, 1000000000, 1}, {15, 3, 0}, {5, 999999995, 0}, {5, 1, 1}});
    }

    public int getNumberOfBacklogOrders(int[][] orders) {
        PriorityQueue<Order> buy = new PriorityQueue<>(Comparator.comparingInt(Order::getPrice).reversed());
        PriorityQueue<Order> sell = new PriorityQueue<>(Comparator.comparingInt(Order::getPrice));
        for (int[] order : orders) {
            int price = order[0];
            int amount = order[1];
            int type = order[2];
            if (type == 0) {
                while (true) {
                    if (amount == 0) {
                        break;
                    }
                    if (sell.isEmpty()) {
                        buy.add(new Order(price, amount));
                        break;
                    }
                    // 无法添加
                    if (sell.peek().price > price) {
                        buy.add(new Order(price, amount));
                        break;
                    }
                    Order minSellOrder = sell.poll();
                    // 执行订单

                    if (minSellOrder.amount < amount) {
                        amount -= minSellOrder.amount;
                    } else {
                        minSellOrder.amount = minSellOrder.amount - amount;
                        sell.add(minSellOrder);
                        amount = 0;
                    }


                }
            } else {

                while (true) {
                    if (amount == 0) {
                        break;
                    }
                    if (buy.isEmpty()) {
                        sell.add(new Order(price, amount));
                        break;
                    }
                    // 无法添加
                    if (buy.peek().price < price) {
                        sell.add(new Order(price, amount));
                        break;
                    }
                    Order maxBuyOrder = buy.poll();

                    if (maxBuyOrder.amount < amount) {
                        amount -= maxBuyOrder.amount;
                    } else {
                        maxBuyOrder.amount = maxBuyOrder.amount - amount;
                        buy.add(maxBuyOrder);
                        amount = 0;
                    }

                }
            }

        }

        long res = 0;
        for (
                Order order : buy) {
            res += order.amount;
        }
        for (
                Order order : sell) {
            res += order.amount;
        }
        return (int) (res % (Math.pow(10, 9) + 7));

    }

}

class Order {
    int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    int amount;

    public Order(int price, int amount) {
        this.price = price;
        this.amount = amount;
    }
}