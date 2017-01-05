//Version 0: original
public class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.charAt(0) == '0') return 0;
        int[] f = new int[s.length()+1];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i <= s.length(); i++) {
            if (s.charAt(i-1) == '0') {
                if (s.charAt(i-2) == '0' || s.charAt(i-2) - '0' > 2) return 0;
                f[i] = f[i-2];
            } else if (!canDecode(s.charAt(i-2), s.charAt(i-1))) {
                f[i] = f[i-1];
            } else {
                f[i] = f[i-1] + f[i-2];
            }
        }
        return f[s.length()];
    }
    private boolean canDecode(char a, char b) {
        if (a == '0') return false;
        int sum = (a - '0') * 10 + (b - '0');
        return sum <= 26;
    }
}

//Version 1: 
//Better using of Integer class!
//@copyright : https://discuss.leetcode.com/topic/35840/java-clean-dp-solution-with-explanation
public class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for(int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i-1, i));
            int second = Integer.valueOf(s.substring(i-2, i));
            if(first >= 1 && first <= 9) {
               dp[i] += dp[i-1];  
            }
            if(second >= 10 && second <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }
}
