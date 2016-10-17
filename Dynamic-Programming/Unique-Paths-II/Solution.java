public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if(m <= 0 || n <= 0){
            return 0;
        }
        if(obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1){
            return 0;
        }
        //state:numOfPaths[i][j] = number of paths from (0,0) to (i,j)
        int[][] numOfPaths = new int[m][n];
        //initialize        
        for(int i = 0; i < m; i++){
            if(obstacleGrid[i][0] == 1){
                break;
            }else{
                numOfPaths[i][0] = 1;
            }
        }
        for(int j = 0; j < n; j++){
            if(obstacleGrid[0][j] == 1){
                break;
            }else{
                numOfPaths[0][j] = 1;
            }
        }
        //top-left to right-down
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(obstacleGrid[i][j] == 1){
                    numOfPaths[i][j] = 0;
                }else{
                    numOfPaths[i][j] = numOfPaths[i-1][j] + numOfPaths[i][j-1];
                }
            }
        }
        return numOfPaths[m-1][n-1];
    }
}
