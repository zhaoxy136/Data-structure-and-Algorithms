//Version 0: DP O(N^2)
class Solution {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        //initializa
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        int count = s.length();
        for (int k = 1; k < s.length(); k++) {
            for (int i = 0; i + k < s.length(); i++) {
                if (s.charAt(i) == s.charAt(i+k) && (k == 1 || dp[i+1][i+k-1])) {
                    dp[i][i+k] = true;
                    count++;
                }
            }
        }
        return count; 
    }
}

//Version 1: O(N^2)
class Solution {
    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            int j = 0;
            while (i - j >= 0 && i + j < s.length() && s.charAt(i-j) == s.charAt(i+j)) j++;
            count += j;
            j = 0;
            while (i - j >= 0 && i + j + 1 < s.length() && s.charAt(i-j) == s.charAt(i+j+1)) j++;
            count += j;
        }
        return count;
    }
}
