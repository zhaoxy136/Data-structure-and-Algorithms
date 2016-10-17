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

//Version 1: Multiple Iteration
//Bottom Up
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0){
            return -1;
        }
        if(triangle.get(0) == null || triangle.get(0).size() == 0){
            return -1;
        }
        //state: sum[x][y] = minimum path sum from (x,y) to the bottom
        int n = triangle.size();
        int[][] sum = new int[n][n];
        
        //Initialize
        for(int i = 0; i < n; i++){
            sum[n - 1][i] = triangle.get(n - 1).get(i);
        }
        
        //bottom up
        for(int i = n - 2; i >= 0; i--){
            for(int j = 0; j <= i; j++){
                sum[i][j] = Math.min(sum[i + 1][j], sum[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        //answer
        return sum[0][0];
    }
}

//Version 2: Memorize Search
public class Solution {
    private int[][] sum;
    private List<List<Integer>> triangle;
    private int n;
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0){
            return -1;
        }
        if(triangle.get(0) == null || triangle.get(0).size() == 0){
            return -1;
        }
        this.triangle = triangle;
        this.sum = new int[n][n];
        this.n = triangle.size();
        //Initialize
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                sum[i][j] = Integer.MAX_VALUE;
            }
        }
        return helper(0,0);
    }
    //find the minimum path sum from (x,y) to bottom
    private int helper(int x, int y){
        if(x >= n){
            return 0;
        }
        if(sum[x][y] != Integer.MAX_VALUE){
            return sum[x][y];
        }
        int left = helper(x+1, y);
        int right = helper(x+1, y+1);
        sum[x][y] = Math.min(left,right) + triangle.get(x).get(y);
        return sum[x][y];
    }
}
