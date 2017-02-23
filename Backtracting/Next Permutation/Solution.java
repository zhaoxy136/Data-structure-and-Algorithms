//Version 0:
public class Solution {
        //1. find last ascending position i;
        //2. find last element greater than i, having position j;
        //3. swap num[i] and nums[j];
        //4. reverse from i+1 to the end;
    public void nextPermutation(int[] nums) {
        if (nums.length < 2) return;
        int i, j;
        for (i = nums.length - 2; (i >= 0 && nums[i] >= nums[i+1]); i--);
        //no next permutation
        if (i >= 0) {
        for (j = nums.length-1; nums[j] <= nums[i]; --j);
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
        }
        reverse(nums, i+1, nums.length - 1);
    }
    private void reverse(int[] nums, int begin, int end) {
        while (begin < end) {
            int temp = nums[begin];
            nums[begin++] = nums[end];
            nums[end--] = temp;
        }
    }
}
