//version 0:

public class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        //state: f[i] = the most value robbing the first i houses 
        int[] f = new int[nums.length + 1];
        //initialize
        f[0] = 0;
        f[1] = nums[0];
        //function: f[i] = max(f[i-1], f[i-2] + nums[i-1])
        for(int i = 2; i <= nums.length; i++){
            f[i] = Math.max(f[i-1], f[i-2] + nums[i-1]);
        }
        //answer
        return f[nums.length];
    }
}
