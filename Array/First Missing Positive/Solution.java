//Version 0:
public class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        int i = -1, j = 0;
        while (j < nums.length) {
            if (nums[j] <= 0) {
                int tmp = nums[++i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
            j++;
        }
        int k = nums.length - (i + 1);
        for (int l = i+1; l < nums.length; l++) {
            if (Math.abs(nums[l]) <= k) {
                int index = i + Math.abs(nums[l]);
                if (nums[index] > 0) nums[index] *= -1;
            }
        }
        for (int l = i+1; l < nums.length; l++) {
            if (nums[l] > 0) return l-i;
        }
        return k+1;
    }
}

//Version 1:
public class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i]-1] != nums[i]) {
                int tmp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = tmp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i+1) return i+1;
        }
        return n+1;
    }
}
