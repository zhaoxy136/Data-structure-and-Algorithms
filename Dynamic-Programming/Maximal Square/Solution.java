//Version 0:DP
public class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int[][] dp = new int[matrix.length+1][matrix[0].length+1];
        int edge = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    dp[i+1][j+1] = Math.min(Math.min(dp[i][j], dp[i][j+1]), dp[i+1][j]) + 1;
                    edge = Math.max(edge, dp[i+1][j+1]);
                }
            }
        }
        return edge * edge;
    }
}

//Version 1:Better DP
public class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int[] dp = new int[matrix[0].length+1];
        int edge = 0;
        int prev = 0;
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                int tmp = dp[j];
                if (matrix[i-1][j-1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j-1], dp[j]), prev) + 1;
                    edge = Math.max(edge, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = tmp;
            }
        }
        return edge * edge;
    }
}
