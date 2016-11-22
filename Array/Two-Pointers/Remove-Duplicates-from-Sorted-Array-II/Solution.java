//version 0: 
public class Solution {
    public int removeDuplicates(int[] nums) {
        int begin = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && i < nums.length - 1 && nums[i-1] == nums[i] && nums[i] == nums[i+1]) {
                continue;
            }
            nums[begin] = nums[i];
            begin++;
        }
        return begin;
    }
}

//Version 1: more adaptive
public class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int num : nums) {
            if (i < 2 || num > nums[i-2]) {
                nums[i++] = num;
            }
        }
        return i;
    }
}
