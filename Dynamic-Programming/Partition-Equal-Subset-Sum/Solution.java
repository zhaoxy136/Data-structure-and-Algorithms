//Version 0:
public class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sumValue = 0;
        for(int i = 0; i < n; i++){
            sumValue += nums[i];
        }
        if(sumValue % 2 == 1){ // total sum has to be an even number
            return false;
        }
        int halfValue = sumValue / 2;
        return findSum(halfValue, nums);
    }
    
    private boolean findSum(int target, int[] nums){
        //state: f[i][j]: whether the first i elements can form exactly number target j
        boolean[][] f = new boolean[nums.length + 1][target + 1];
        //initialize
        for(int i = 0; i <= nums.length; i++){
            f[i][0] = true;
        }
        //function: f[i][j] = f[i-1][j-nums[i-1]] || f[i-1][j];
        for(int i = 1; i <= nums.length; i++){
            for(int j = target; j >= nums[i-1]; j--){
                f[i][j] = f[i-1][j-nums[i-1]] || f[i-1][j];
            }
        }
        //answer
        return f[nums.length][target];
    }
    
}

//Version 1: 
public class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sumValue = 0;
        for(int i = 0; i < n; i++){
            sumValue += nums[i];
        }
        if(sumValue % 2 == 1){ // total sum has to be an even number
            return false;
        }
        int halfValue = sumValue / 2;
        return findSum(halfValue, nums);
    }
    
    private boolean findSum(int target, int[] nums){
        //state: f[i]: whether the elements can form exactly number target
        boolean[] f = new boolean[target + 1];
        //initialize
        f[0] = true;
        //function: f[j] = f[j-nums[i]] || f[j];
        for(int i = 0; i < nums.length; i++){
            for(int j = target; j >= nums[i]; j--){
                f[j] = f[j-nums[i]] || f[j];
            }
        }
        //answer
        return f[target];
    }
}
