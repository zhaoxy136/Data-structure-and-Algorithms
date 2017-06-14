//Version 0:
public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int[] max = new int[2];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 1; (i-j >= 0 && i+j < s.length()); j++) {
                if (s.charAt(i-j) != s.charAt(i+j)) break;
                if (2*j+1 > max[1]-max[0]+1) {
                    max[0] = i-j;
                    max[1] = i+j;
                }
            }
        }
        
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; (i-j >= 0 && i+j+1 < s.length()); j++) {
                if (s.charAt(i-j) != s.charAt(i+j+1)) break;
                if (2*j+2 > max[1]-max[0]+1) {
                    max[0] = i-j;
                    max[1] = i+j+1;
                }
            }
        }
        return s.substring(max[0], max[1]+1);
    }
}

//Version 1:
public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int maxLen = 1;
        String res;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        res = ""+s.charAt(0);
        for (int len = 1; len < s.length(); len++) {
            for (int i = 0; i+len < s.length(); i++) {
                if (s.charAt(i) == s.charAt(i+len) && (len == 1 || dp[i+1][i+len-1])) {
                    dp[i][i+len] = true;
                    if (len+1 > maxLen) {
                        maxLen = len+1;
                        res = s.substring(i, i+len+1);
                    }
                }
            }
        }
        return res;
    }
}

