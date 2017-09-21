//Version 0: three-step-reverse //handle negative k
class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return;
        k = k % nums.length;
        if (k < 0) {
            rotate(nums, nums.length + k);
        } else if (k > 0) {
            reverse(nums, 0, nums.length - k - 1);
            reverse(nums, nums.length - k, nums.length - 1);
            reverse(nums, 0, nums.length - 1);
        }
    }
    
    public void reverse(int[] nums, int s, int e) {
        while (s < e) {
            int tmp = nums[s];
            nums[s++] = nums[e];
            nums[e--] = tmp;
        }
    }
}

//Version 1: cyclic update
class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return;
        if (k % nums.length == 0) return;
        if (k < 0) {
            rotate(nums, nums.length + k);
        } else {
            int count = 0;
            for (int i = 0; i < nums.length && count < nums.length; i++) {
                int index = i;
                int prev = nums[i];
                while ((index + k) % nums.length != i) {
                    index = (index + k) % nums.length;
                    int tmp = nums[index];
                    nums[index] = prev;
                    count++;
                    prev = tmp;
                }
                nums[i] = prev;
                count++;
            }
        }
    }
}
