//Version 0: Dynamic Programming O(N^2)
public class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] f = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            f[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }
        int res = f[0];
        for (int i = 1; i < nums.length; i++) {
            res = Math.max(res, f[i]);
        }
        return res;
    }
}

//Version 1: Binary Search @copyright https://www.felix021.com/blog/read.php?1587
public class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] minLast = new int[nums.length];
        minLast[0] = nums[0];
        int len = 1;
        for (int i = 1; i < nums.length; i++) {
            int index = binarySearch(minLast, 0, len-1, nums[i]);
            minLast[index] = nums[i];
            if (len < index + 1) {
                len = index + 1;
            }
        }
        return len;
    }
    
    private int binarySearch(int[] nums, int start, int end, int target) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] >= target) {
            return start;
        }
        if (nums[end] >= target) {
            return end;
        }
        return end + 1;
    }
}
