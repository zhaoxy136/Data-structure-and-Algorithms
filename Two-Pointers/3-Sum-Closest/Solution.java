public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int i = 0;
        int res = Integer.MAX_VALUE;
        int sum = nums[0] + nums[1] + nums[2];
        while (i < nums.length - 2) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int val = nums[i] + nums[j] + nums[k];
                if (Math.abs(val - target) < res) {
                    sum = val;
                    res = Math.abs(val - target);
                }
                if (val == target) {
                    return target;
                }
                if (val < target) {
                    while (nums[j] == nums[++j] && j < k);
                } else {
                    while (nums[k--] == nums[k] && j < k);
                }
            }
            while (nums[i] == nums[++i] && i < nums.length - 2);
        }
        return sum;
    }
}
