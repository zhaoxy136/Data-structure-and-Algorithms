//Version 0: original recursion
public class Solution {
    public boolean PredictTheWinner(int[] nums) {
        return helper(nums, 0, nums.length-1, 0, true);
    }
    private boolean helper(int[] nums, int start, int end, int scoreDif, boolean turn) {
        if (start > end) {
            if (turn) return scoreDif >= 0;
            return scoreDif > 0;
        }
        //scoreDif += nums[start];
        return (!helper(nums, start+1, end, -1 * scoreDif-nums[start], !turn) || !helper(nums, start, end-1, -1 * scoreDif-nums[end], !turn));
        
    }
}

//Version 1: DP
public class Solution {
    public boolean PredictTheWinner(int[] nums) {
        return helper(nums, 0, nums.length-1, new Integer[nums.length][nums.length]) >= 0;
    }
    //how much the first player could get more when array from start to end
    private int helper(int[] nums, int start, int end, Integer[][] dp) {
        if (dp[start][end] == null) {
            dp[start][end] = start == end ? nums[end] : Math.max(nums[start]-helper(nums,start+1,end,dp), nums[end]-helper(nums,start,end-1,dp));
        }
        return dp[start][end];
    }
}
