package Atlassian;

public class BestTimeToBuyStock {
    public int maxProfit(int[] prices) {
        /**
         * Sell > buy
         * Take two pointers one for buy(left) and other for sell(right) and calc profit
         * at each iteration
         *
         */

        int left = 0, right = 0, profit = 0;

        while (right < prices.length) {
            if (prices[left] <= prices[right]) {
                profit = Math.max((prices[right] - prices[left]), profit);
            } else {
                left = right;
            }
            right++;
        }

        return profit;

    }
}
