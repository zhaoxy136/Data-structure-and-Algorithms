//Version 0: original, O(1) space
public class Solution {
    public int arrayNesting(int[] nums) {
        int res = 0;
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] < 0) continue;
            int count = 1;
            int next = nums[k];
            nums[k] *= -1;
            while (next != k) {
                next = Math.abs(nums[next]);
                count++;
                nums[next] *= -1;
            }
            res = Math.max(res, count);
        }
        return res;
    }
}

//Version 1: same idea
public class Solution {
    public int arrayNesting(int[] nums) {
        int res = 0;
        for (int k = 0; k < nums.length; k++) {
            int count = 0;
            int next = k;
            while (nums[next] != Integer.MAX_VALUE) {
                int tmp = next;
                next = nums[next];
                count++;
                nums[tmp] = Integer.MAX_VALUE;
            }
            res = Math.max(res, count);
        }
        return res;
    }
}
