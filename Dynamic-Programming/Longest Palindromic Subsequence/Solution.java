//Version 0:
public class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        for (int len = 1; len < s.length(); len++) {
            for (int i = 0; i + len < s.length(); i++) {
                if (s.charAt(i) == s.charAt(i+len)) {
                    dp[i][i+len] = dp[i+1][i+len-1] + 2;
                } else {
                    dp[i][i+len] = Math.max(dp[i+1][i+len], dp[i][i+len-1]);
                }
            }
        }
        return dp[0][s.length()-1];
    }
}
