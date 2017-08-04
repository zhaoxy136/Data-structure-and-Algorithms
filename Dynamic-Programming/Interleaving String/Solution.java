//Version 0: origianl 1-D array DP
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        boolean[] dp = new boolean[s2.length()+1];
        dp[0] = true;
        for (int i = 1; i <= s2.length() && s2.charAt(i-1) == s3.charAt(i-1); i++) {
            dp[i] = true;
        }
        for (int i = 1; i <= s1.length(); i++) {
            dp[0] &= s1.charAt(i-1) == s3.charAt(i-1);
            for (int j = 1; j <= s2.length(); j++) {
                boolean tmp = dp[j];
                dp[j] = false;
                if (s1.charAt(i-1) == s3.charAt(i+j-1)) {
                    dp[j] |= tmp;
                }
                if (s2.charAt(j-1) == s3.charAt(i+j-1)) {
                    dp[j] |= dp[j-1];
                }
            }
        }
        return dp[s2.length()];
    }
}
