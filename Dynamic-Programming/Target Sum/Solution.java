//Version 0: recursion
public class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        return helper(nums, S, 0);
    }
    private int helper(int[] nums, int S, int pos) {
        if (pos == nums.length) return S == 0 ? 1 : 0;
        return helper(nums, S+nums[pos], pos+1) + helper(nums, S-nums[pos], pos+1);
    }
}

//Version 1: DP
