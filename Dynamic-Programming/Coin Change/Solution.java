//Version 0: 
public class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] f = new int[amount+1];
        f[0] = 0;
        for (int i = 1; i <= amount; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i && f[i-coins[j]] != Integer.MAX_VALUE) {
                    f[i] = Math.min(f[i], f[i-coins[j]] + 1);
                }
            }
        }
        return f[amount] == Integer.MAX_VALUE ? -1 : f[amount];
    }
}

//Version 1: 
// Initialize the dp[i] as amount+1 is smarter!
public class Solution {
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;             
        int[] dp = new int[amount + 1];  
        Arrays.fill(dp, max);  
        dp[0] = 0;   
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
