public class Solution {
    public int uniquePaths(int m, int n) {
        if(m <= 0 || n <= 0){
            return 0;
        }
        //state: numOfPath[i][j] = number of paths from (0,0) to (i,j)
        int[][] numOfPaths = new int[m][n];
        //initialize
        numOfPaths[0][0] = 1;
        for(int i = 1; i < m; i++){
                numOfPaths[i][0] = 1;
        }
        for(int j = 0; j < n; j++){
            numOfPaths[0][j] = 1;
        }
        //top down
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                numOfPaths[i][j] = numOfPaths[i-1][j] + numOfPaths[i][j-1];
            }
        }
        return numOfPaths[m-1][n-1];
    }
}
