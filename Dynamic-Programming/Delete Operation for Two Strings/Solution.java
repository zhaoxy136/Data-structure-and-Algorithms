//Version 0:
public class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        if (len1 * len2 == 0) return len1+len2;
        int[][] dp = new int[len1+1][len2+1];
        for (int i = 0; i < len1; i++) {
            dp[i+1][0] = 0;
        }
        for (int i = 0; i < len2; i++) {
            dp[0][i+1] = 0;
        }
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j] + 1;
                } else {
                    dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
                }
            }
        }
        return len1+len2-2 * dp[len1][len2];
    }
}

//
