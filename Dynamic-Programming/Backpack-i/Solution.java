//Version 0: 
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        //state: f[i][j] : how full can first i items putting in capacity of j
        //i := 0 to n; j := 0 to m
        int[][] f = new int[A.length + 1][m + 1];
        //initialize:
        for(int i = 0; i <= m; i++){
            f[0][i] = 0;
        }
        for(int i = 1; i <= A.length; i++){
            f[i][0] = 0;
        }
        //function: f[i][j] = max(f[i-1][j], f[i-1][j-A[i-1]] + A[i-1])
        for(int i = 1; i <= A.length; i++){
            for(int j = 1; j <= m ; j++){
                f[i][j] = f[i-1][j];
                if(j >= A[i-1]){
                    f[i][j] = Math.max(f[i-1][j], f[i-1][j-A[i-1]] + A[i-1]);
                }
            }
        }
        //answer
        return f[A.length][m];
    }
}

Version 1: 
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        //state: f[i]: whether these items can be exactly capacity of i 
        //               within a backpack of m
        boolean[] f = new boolean[m+1];
        //initialize
        f[0] = true; // others are false, since with no item, only 0 capacity can be fill
        //function: 
        for(int j = 0; j < A.length; j++){
            for(int i = m; i >= A[j]; i--){
                f[i] = f[i] || f[i-A[j]];
            }
        }
        //answer
        for(int i = m; i >= 0; i--){
            if(f[i]){
                return i;
            }
        }
        return 0;
    }
}


