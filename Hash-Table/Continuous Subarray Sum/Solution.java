//Version 0:
public class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int preSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            if (k != 0) preSum %= k;
            if (map.containsKey(preSum)) {
                if (i - map.get(preSum) > 1) return true;
            } else {
                map.put(preSum, i);
            }
        }
        return false;
    }
}
