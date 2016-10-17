//Version 0: Multiple Iteration
//Top Down
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0){
            return -1;
        }
        if(triangle.get(0) == null || triangle.get(0).size() == 0){
            return -1;
        }
        //state: sum[x][y] = minimum path sum from (0,0) -> (x,y)
        int n = triangle.size();
        int[][] sum = new int[n][n];
        
        //Initialize
        sum[0][0] = triangle.get(0).get(0);
        for(int i = 1; i < n; i++){
            sum[i][0] = sum[i - 1][0] + triangle.get(i).get(0);
            sum[i][i] = sum[i - 1][i - 1] + triangle.get(i).get(i);
        }
        
        //top down
        for(int i = 1; i < n; i++){
            for(int j = 1; j < i; j++){
                sum[i][j] = Math.min(sum[i - 1][j - 1], sum[i - 1][j]) + triangle.get(i).get(j);
            }
        }
        //answer
        int result = sum[n - 1][0];
        for(int i = 0; i < n; i++){
            if(sum[n - 1][i] < result){
                result = sum[n - 1][i];
            }
        }
        return result;
    }
}
