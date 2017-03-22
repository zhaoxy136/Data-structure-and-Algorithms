Version 0: two pointers
public class Solution {
    public int findPairs(int[] nums, int k) {
        if (nums.length < 2 || k < 0) return 0;
        Arrays.sort(nums);
        int count = 0;
        int i = 0, j = 1;
        while (j < nums.length) {
            if (i == j) j++;
            else if (nums[j] - nums[i] > k) i++;
            else {
                if (nums[j] - nums[i] == k) count++;
                while (++j < nums.length && nums[j] == nums[j-1]);
            }
        }
        return count;
    }
}
