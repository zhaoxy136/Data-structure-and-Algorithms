//Version: 0 
/**
 *Basic idea: consider two separate robbery
 *one without the first house
 *the other without the last house
 *compare them and get the answer
 */
public class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return nums[0];
        }
        return Math.max(helper(nums,0,n-2), helper(nums,1,n-1));
    }
    
    public int helper(int[] nums, int start, int end){
        //state: f[k]: max value of first k elements
        int[] f = new int[end-start+2];
        //initializfe
        f[0] = 0;
        f[1] = nums[start];
        //function
        for(int k = 2; k <= end-start+1; k++){
            f[k] = Math.max(f[k-1], f[k-2] + nums[start+k-1]);
        }
        //answer
        return f[end-start+1];
    }
}
