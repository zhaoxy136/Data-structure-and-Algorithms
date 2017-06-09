//Version 0: prefix sum
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] preSum = new int[nums.length+1];
        preSum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
        }
        int low = 0;
        int high = 1;
        int len = Integer.MAX_VALUE;
        while (low < high && high < preSum.length) {
            if (preSum[high] - preSum[low] >= s) {
                len = Math.min(len, high - low);
                low++;
            } else {
                high++;
            }
        }
        return len == Integer.MAX_VALUE ? 0 : len;
    }
}

//Version 1: more elegant
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int low = 0;
        int high = 0;
        int sum = 0;
        int len = Integer.MAX_VALUE;
        while (high < nums.length) {
            sum += nums[high++];
            while (sum >= s) {
                len = Math.min(len, high-low);
                sum -= nums[low++];
            }
        }
        return len == Integer.MAX_VALUE ? 0 : len;
    }
}
