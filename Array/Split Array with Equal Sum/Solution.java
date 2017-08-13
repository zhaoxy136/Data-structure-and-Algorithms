public class Solution {
    public boolean splitArray(int[] nums) {
        if (nums.length < 7) return false;
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i-1] + nums[i];
        }
        for (int j = 3; j < nums.length - 3; j++) {
            Set<Integer> set = new HashSet<>();
            for (int i = 1; i < j - 1; i++) {
                if (sum[i-1] == sum[j-1] - sum[i]) {
                    set.add(sum[i-1]);
                }
            }
            for (int k = nums.length - 2; k > j + 1; k--) {
                if (sum[k-1] - sum[j] == sum[nums.length - 1] - sum[k] && set.contains(sum[k-1] - sum[j])) return true;
            }
        }
        return false;
    }
}
