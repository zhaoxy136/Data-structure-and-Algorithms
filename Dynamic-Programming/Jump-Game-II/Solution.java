//Version 0: DP
public class Solution {
    public int jump(int[] nums) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        //state: f[i] = minimum steps from 0 to i
        int[] f = new int[nums.length];
        //initialize
        f[0] = 0;
        for(int i = 1; i < nums.length; i++){
            f[i] = Integer.MAX_VALUE;
        }
        //function:f[i] = min(f[j] + 1); j < i && j + nums[j] >= i
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] + j >= i){
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
        }
        //answer
        return f[nums.length - 1];
    }
}

//Version 1: Greedy
public class Solution {
    public int jump(int[] nums) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int jumps = 0;
        int start = 0, end = 0;
        while(end < nums.length - 1){
            jumps++;
            int farthest = end;
            for(int i = start; i <= end; i++){
                if(nums[i] + i > farthest){
                    farthest = nums[i] + i;
                }
            }
            start = end + 1;
            end = farthest;
        }
        return jumps;
    }
}
