//Version 0: 
public class Solution {
    public int longestLine(int[][] M) {
        if (M == null || M.length == 0) return 0;
        int res = 0;
        int[] hor = new int[M[0].length+2];
        int[] ver = new int[M[0].length+2];
        int[] dia = new int[M[0].length+2];
        int[] redia = new int[M[0].length+2];
        for (int i = 1; i <= M.length; i++) {
            int prev = 0;
            hor[0] = 0;
            for (int j = 1; j <= M[0].length; j++) {
                int tmp = dia[j];
                if (M[i-1][j-1] == 0) {
                    hor[j] = 0;
                    ver[j] = 0;
                    dia[j] = 0;
                    redia[j] = 0;
                } else {
                    hor[j] = hor[j-1] + 1;
                    ver[j] = ver[j] + 1;
                    dia[j] = prev + 1;
                    redia[j] = redia[j+1] + 1;
                    res = Math.max(res, ver[j]);
                    res = Math.max(res, dia[j]);
                    res = Math.max(res, redia[j]);
                    res = Math.max(res, hor[j]);
                }
                prev = tmp;
            }
            
        }
        
        return res;
    }
}

//Version 1: using two-d array
public class Solution {
    public int longestLine(int[][] M) {
        if (M == null || M.length == 0) return 0;
        int res = 0;
        int[][] dp = new int[M[0].length+2][4];
        for (int i = 1; i <= M.length; i++) {
            int prev = 0;
            dp[0][0] = 0;
            for (int j = 1; j <= M[0].length; j++) {
                int tmp = dp[j][2];
                if (M[i-1][j-1] == 0) {
                    dp[j][0] = 0;
                    dp[j][1] = 0;
                    dp[j][2] = 0;
                    dp[j][3] = 0;
                } else {
                    dp[j][0] = dp[j-1][0] + 1;
                    dp[j][1] = dp[j][1] + 1;
                    dp[j][2] = prev + 1;
                    dp[j][3] = dp[j+1][3] + 1;
                    res = Math.max(res, dp[j][0]);
                    res = Math.max(res, dp[j][1]);
                    res = Math.max(res, dp[j][2]);
                    res = Math.max(res, dp[j][3]);
                }
                prev = tmp;
            }
        }
        return res;
    }
}
