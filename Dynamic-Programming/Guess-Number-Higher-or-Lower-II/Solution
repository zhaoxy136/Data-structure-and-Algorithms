public class Solution {
    public int getMoneyAmount(int n) {
        if (n == 1){
            return 0;
        }
        //state: f[i][j] : the max cost guarantee to win choosing from i to j
        int[][] f = new int[n + 1][n + 1];
        //initialize
        for (int i = 0; i <= n; i++){
            for (int j = i+1; j <= n; j++){
                f[i][j] = Integer.MAX_VALUE;
            }
        }
        //function: f[i][j] = min(i<= k <= j; k + max(f[i][k-1], f[k+1][j]);
        for (int length = 1; length <= n; length++){
            for (int i = 0; i + length <= n; i++){
                for(int k = i; k <= i + length; k++){
                    f[i][i+length] = Math.min(f[i][i + length], k + 
                            Math.max((k == i ? 0 : f[i][k-1]), (k == i+length ? 0 : f[k+1][i+length])));
                }
            }
        }
        //answer
        return f[1][n];
    }
}
