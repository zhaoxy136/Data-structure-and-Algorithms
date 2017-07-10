//Version 0:
public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int start = -1, end = -1;
        int max = nums[0];
        int min = nums[nums.length-1];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= max) {
                max = nums[i];
            } else {
                end = i;
            }
        }
        for (int j = nums.length-1; j >= 0; j--) {
            if (nums[j] <= min) {
                min = nums[j];
            } else {
                start = j;
            }
        }
        return start == end ? 0 : end-start+1;
    }
}

//Version 1:
@copyright: https://discuss.leetcode.com/topic/89282/java-o-n-time-o-1-space
public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int start = -1, end = -1;
        int max = nums[0];
        int min = nums[nums.length-1];
        for (int i = 0, j = nums.length-1; i < nums.length; i++, j--) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[j]);
            if (nums[i] < max) end = i;
            if (nums[j] > min) start = j;
        }
        return start == end ? 0 : end-start+1;
    }
}

