//very good explanation: https://soulmachine.gitbooks.io/algorithm-essentials/java/dp/best-time-to-buy-and-sell-stock-with-cooldown.html

public class Solution {
    public int maxProfit(int[] prices) {
        //sell[i] = max{sell[i-1], buy[i-1] + price[i]}
        //buy[i] = max{buy[i-1], sell[i-2] - price[i]}
        if (prices.length < 2) return 0;
        int prevSell = 0, curSell = 0;
        int curBuy = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            int tmp = curSell;
            curSell = Math.max(curSell, curBuy + prices[i]);
            curBuy = Math.max(curBuy, prevSell - prices[i]);
            prevSell = tmp;
        }
        return curSell;
    }
}
