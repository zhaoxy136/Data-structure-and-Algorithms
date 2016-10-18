//Version 0: Dynamic Programming
public class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        if(nums == null || n == 0){
            return false;
        }
        //state: jump[i] = is able to jump to the ith position
        boolean[] jump = new boolean[n];
        //initialize
        jump[0] = true;
        
        //TODOFunction
        for(int i = 1; i < n; i++){
            for(int j = i - 1; j >= 0 ; j--){  //Note: we use j-- instead of j++ to decrease the operatioins in most general occations
                if(jump[j] && nums[j] + j >= i){
                    jump[i] = true;
                    break;
                }
            }
        }
        return jump[n-1];
    }
}

//Version 1: Greedy

public class Solution {
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0){
            return false;
        }
        int farthest = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(i <= farthest && nums[i] + i > farthest){
                farthest = nums[i] + i;
            }
        }
        return farthest >= nums.length - 1;

    }
}
