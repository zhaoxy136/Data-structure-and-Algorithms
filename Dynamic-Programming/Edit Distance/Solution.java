//Version 0:
//@copyright:https://discuss.leetcode.com/topic/17639/20ms-detailed-explained-c-solutions-o-n-space
public class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int i = 1; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= word2.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}

//Version 1: better DP
public class Solution {
    public int minDistance(String word1, String word2) {
        if (word1.length() == 0 || word2.length() == 0) return word1.length() + word2.length();
        int[] dp = new int[word2.length()+1];
        for (int j = 1; j <= word2.length(); j++) {
            dp[j] = j;
        }
        for (int i = 1; i <= word1.length(); i++) {
            int prev = dp[0];
            dp[0] = i;
            for (int j = 1; j <= word2.length(); j++) {
                int tmp = dp[j];
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[j] = prev;
                } else {
                    dp[j] = Math.min(Math.min(prev, dp[j]), dp[j-1]) + 1;
                }
                prev = tmp;
            }
        }
        return dp[word2.length()];
    }
}
