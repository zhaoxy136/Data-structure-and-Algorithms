public class Solution {
    public int minCost(int[][] costs) {
        int[] dp = new int[3];
        for (int i = 0; i < costs.length; i++) {
            int tmp1 = dp[0], tmp2 = dp[1];
            dp[0] = Math.min(dp[1], dp[2]) + costs[i][0];
            dp[1] = Math.min(tmp1, dp[2]) + costs[i][1];
            dp[2] = Math.min(tmp1, tmp2) + costs[i][2];
        }
        return Math.min(dp[0], Math.min(dp[1], dp[2]));
    }
}
