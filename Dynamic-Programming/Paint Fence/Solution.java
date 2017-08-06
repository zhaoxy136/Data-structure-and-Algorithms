//dp[i][0] -- paint from 0 to ith fence and ith has same color with previous one, i.e. i-1 th
//dp[i][1] -- paint from 0 to ith fence and ith has different color with previous one.
public class Solution {
    public int numWays(int n, int k) {
        if (n * k == 0) return 0;
        int same = 0;
        int dif = k;
        // dp[0][0] = 0;
        // dp[0][1] = k;
        for (int i = 1; i < n; i++) {
            // dp[i][0] = dp[i-1][1];
            // dp[i][1] = dp[i-1][0] * (k-1) + dp[i-1][1] * (k-1);
            int tmp = dif;
            dif = (same + dif) * (k-1);
            same = tmp;
        }
        return same + dif;
    }
}
