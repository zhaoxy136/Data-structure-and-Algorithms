//Version 0:
class Solution {
    public int findShortestSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int degree = 0;
        int minLen = nums.length;
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int[] tmp = map.getOrDefault(nums[i], new int[]{0, i});
            tmp[0]++;
            if (tmp[0] > degree) {
                degree = tmp[0];
                minLen = i - tmp[1] + 1;
            } else if (tmp[0] == degree) {
                minLen = Math.min(minLen, i - tmp[1] + 1);
            }
            map.put(nums[i], tmp);
        }
        return minLen;
    }
}
