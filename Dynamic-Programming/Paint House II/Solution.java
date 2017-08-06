//Version 0: DP original
public class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int k = costs[0].length;
        int[] dp = new int[k];
        int min1 = 0, min2 = 0;
        for (int i = 0; i < costs.length; i++) {
            int tmp1 = min1, tmp2 = min2;
            min1 = Integer.MAX_VALUE;
            min2 = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                if (dp[j] == tmp1) {
                    dp[j] = tmp2 + costs[i][j];
                } else {
                    dp[j] = tmp1 + costs[i][j];
                }
                if (dp[j] < min1) {
                    min2 = min1;
                    min1 = dp[j];
                } else if (dp[j] < min2) {
                    min2 = dp[j];
                }
            }
        }
        return min1;
    }
}

//Version 1: DP, using O(1)space
public class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int min1 = -1, min2 = -1;
        for (int i = 0; i < costs.length; i++) {
            int tmp1 = min1, tmp2 = min2;
            min1 = -1;
            min2 = -1;
            for (int j = 0; j < costs[0].length; j++) {
                if (j == tmp1) {
                    costs[i][j] += tmp2 < 0 ? 0 : costs[i-1][tmp2];
                } else {
                    costs[i][j] += tmp1 < 0 ? 0 : costs[i-1][tmp1];
                }
                if (min1 < 0 || costs[i][j] < costs[i][min1]) {
                    min2 = min1;
                    min1 = j;
                } else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
                    min2 = j;
                }
            }
        }
        return costs[costs.length-1][min1];
    }
}
