//Version 0: DP+memorization
class Solution {
    int[][] memo;
    int[] dir = new int[]{0, 1, 0, -1, 0};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int res = 0;
        memo = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                res = Math.max(res, helper(matrix, i, j));
            }
        }
        return res;
    }
    
    public int helper(int[][] matrix, int x, int y) {
        if (memo[x][y] != 0) return memo[x][y];
        int length = 1;
        for (int i = 0; i < 4; i++) {
            int row = x + dir[i];
            int col = y + dir[i+1];
            if (row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length && matrix[row][col] > matrix[x][y]) {
                length = Math.max(length, helper(matrix, row, col) + 1);
            }
        }
        memo[x][y] = length;
        return length;
    }
}
