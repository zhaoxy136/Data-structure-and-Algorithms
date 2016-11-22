//Version 0: 
public class Solution {
    public int removeDuplicates(int[] nums) {
        int begin = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i-1]) {
                nums[begin++] = nums[i];
            }
        }
        return begin;
    }
}
