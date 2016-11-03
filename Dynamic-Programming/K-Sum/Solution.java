public class Solution {
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return an integer
     */
    public int kSum(int A[], int k, int target) {
        int n = A.length;
        //state: f[i][j][t] represents how many combinations that within first i elements 
        //        j sum equals to t
        int[][][] f = new int[n+1][k+1][target+1];
        //initialize
        for(int i = 0; i <= n; i++){
            f[i][0][0] = 1;
        }
        //function:
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= k && j <= i; j++){
                for(int t = 1; t <= target; t++){
                    if(t >= A[i-1]){
                        f[i][j][t] = f[i-1][j-1][t-A[i-1]];
                    }
                    f[i][j][t] += f[i-1][j][t];
                }
            }
        }
        //answer
        return f[n][k][target];
    }
}
