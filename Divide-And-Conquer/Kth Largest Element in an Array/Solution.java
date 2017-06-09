//1. tranfer kth largest to (n-k+1)th smallest, which will be more suitable for the partition algorithm
//2. use partition algorithm, select last as pivot
//compare i-start+1 with k, determine continue with which part or get the answer
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return quickSelect(nums, 0, n-1, n-k+1);
    }
    private int quickSelect(int[] nums, int start, int end, int k) {
        int i = start - 1;
        int pv = nums[end];
        for (int j = start; j < end; j++) {
            if (nums[j] <= pv) {
                swap(nums, ++i, j);
            }
        }
        nums[end] = nums[++i];
        nums[i] = pv;
        int m = i - start + 1;
        if (m == k) return nums[i];
        if (m > k) return quickSelect(nums, start, i-1, k);
        else return quickSelect(nums, i+1, end, k-m);
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
