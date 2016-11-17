//Version 0: three-step-reverse
public class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0){
            return;
        }
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n-k-1);
        reverse(nums, n-k, n-1);
        reverse(nums, 0, n-1);
        
    }
    private void reverse(int[] nums, int start, int end){
        if (start > end){
            return;
        }
        int mid = start + (end - start) / 2;
        for (int i = start; i <= mid; i++){
            int tmp = nums[i];
            nums[i] = nums[end+start-i];
            nums[end+start-i] = tmp;
        }
    }
}

