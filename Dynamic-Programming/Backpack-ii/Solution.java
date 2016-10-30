//Version 0: O(mn) extra space
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int V[]) {
        //state: f[i][j]:  前i件物品放在体积为j的背包里,最多能放多少价值 
        int[][] f = new int[A.length + 1][m+1];
        //initialize
        for(int i = 0; i <= m; i++){
            f[0][i] = 0;
        }
        //function f[i][j] = max(f[i-1][j], f[i-1][j-A[i-1]] + A[i-1])
        for(int i = 1; i <= A.length; i++){
            for(int j = m; j >= 0; j--){
                f[i][j] = f[i-1][j];
                if(j >= A[i-1]){
                f[i][j] = Math.max(f[i-1][j], f[i-1][j-A[i-1]] + V[i-1]);
                }
            }
        }
        //answer
        return f[A.length][m];
    }
}

//Version 1: O(m) extra space
public class Solution {
    public int backPackII(int m, int[] A, int V[]) {
        int[] f = new int[m+1];
        for(int i = 0; i <= m; i++){
            f[i] = 0;
        }
        for(int i = 0; i < A.length; i++){
            for(int j = m; j >= A[i]; j--){
                f[j] = Math.max(f[j], f[j-A[i]] + V[i]);
            }
        }
        return f[m];
    }
}


