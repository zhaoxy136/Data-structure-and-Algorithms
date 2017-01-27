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

//Version 1: DP ,very smart!
//@copyright:https://discuss.leetcode.com/topic/76243/java-15-ms-c-3-ms-o-ns-iterative-dp-solution-using-subset-sum-with-explanation/2
public class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < S || (sum + S) % 2 == 1) return 0;
        return helper(nums, (sum+S)/2);
    }
    private int helper(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int n : nums) {
            for (int i = target; i >= n; i--) {
                dp[i] += dp[i-n];
            }
        }
        return dp[target];
    }
}
