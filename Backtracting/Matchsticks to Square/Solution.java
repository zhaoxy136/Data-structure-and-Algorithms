//Version 0:
public class Solution {
    public boolean makesquare(int[] nums) {
        long sum = 0l;
        for (int n : nums) sum += n;
        if (nums == null || nums.length == 0 || sum % 4 != 0) return false;
        int edge = (int)(sum / 4);
        Arrays.sort(nums);
        return helper(nums, new int[4], edge, nums.length-1);
    }
    private boolean helper(int[] nums, int[] edge, int target, int index) {
        if (edge[0] == target && edge[1] == target && edge[2] == target) return true;
            for (int j = 0; j < 4; j++) {
                if (edge[j] + nums[index] > target) continue;
                edge[j] += nums[index];
                if (helper(nums, edge, target, index-1)) return true;
                edge[j] -= nums[index];
            }
        return false;
    }
}
