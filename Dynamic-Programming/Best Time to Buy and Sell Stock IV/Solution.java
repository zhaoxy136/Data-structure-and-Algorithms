public class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices.length / 2 < k) return quickSolve(prices);
        int[][] dp = new int[prices.length][k+1];
        for (int j = 1; j <= k; j++) {
            int prevBuy = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                dp[i][j] = Math.max(dp[i-1][j], prevBuy + prices[i]);
                prevBuy = Math.max(prevBuy, dp[i-1][j-1] - prices[i]);
            }
        }
        return dp[prices.length-1][k];
    }
    
    public int quickSolve(int[] prices) {
        int res = 0;
        for (int i = 0; i < prices.length-1; i++) {
            if (prices[i] < prices[i+1]) {
                res += prices[i+1] - prices[i];
            }
        }
        return res;
    }
}
