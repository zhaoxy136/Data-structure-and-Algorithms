//copyright:https://discuss.leetcode.com/topic/30746/share-some-analysis-and-explanations/2
public class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length+2;
        int[][] dp = new int[n][n];
        
        for (int k = 2; k < n; k++) {
            for (int left = 0; left + k < n; left++) {
                int right = left + k;
                for (int pos = left+1; pos < right; pos++) {
                    int coin = (left == 0 ? 1 : nums[left-1]) * nums[pos-1] * (right == n-1 ? 1 : nums[right-1]);
                    dp[left][right] = Math.max(dp[left][right], dp[left][pos] + dp[pos][right] + coin);
                }
            }
        }
        return dp[0][n-1];
    }
}
