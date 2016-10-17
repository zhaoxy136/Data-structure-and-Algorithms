public class Solution {
    public int climbStairs(int n) {
        //state: f[x] = number of distinct ways to get to the xth step
        int[] f = new int[3];
        //initialize
        f[0] = 1;
        f[1] = 1;
        // relation function
        for(int i = 2; i <= n; i++){
            f[i%3] = f[(i-1)%3] + f[(i-2)%3];
        }
        //answer
        return f[n%3];
    }
}
