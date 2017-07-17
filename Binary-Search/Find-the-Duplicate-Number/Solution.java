//Version 0: original
public class Solution {
    public int findDuplicate(int[] nums) {
        if (nums.length < 2){
            return 0;
        }
        return helper(nums, 0, nums.length-1);
    }
    
    private int helper (int[] nums, int start, int end) {
        if (start >= end){
            return 0;
        }
        int mid = start + (end - start) / 2;
        int left = helper(nums, start, mid);
        int right = helper(nums, mid + 1, end);
        if (left != 0 || right != 0) {
            return left == 0 ? right : left;
        }
        for (int i = start; i <= mid; i++) {
            for (int j = mid + 1; j <= end; j++){
                if (nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }
        return 0;
    }
}

//Version 1: binary search
public class Solution {
    public int findDuplicate(int[] nums) {
        int start = 1;
        int end = nums.length-1; // high = n
        while (start < end) {
            int mid = start + (end - start) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            if (count <= mid) {
                start= mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}
