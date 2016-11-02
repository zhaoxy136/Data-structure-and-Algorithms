//Version 0: prefix sum
public class Solution {
    public int maxSubArray(int[] nums) {
        if(nums.length == 0){
            return Integer.MIN_VALUE;
        }
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int minSum = 0;
        for(int num: nums){
            sum += num;
            max = Math.max(max,sum - minSum);
            minSum = Math.min(sum, minSum);
        }
        return max;
    }
}

//Version 1: Greedy
public class Solution {
    public int maxSubArray(int[] A) {
        if (A == null || A.length == 0){
            return 0;
        }
        int max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            max = Math.max(max, sum);
            sum = Math.max(sum, 0);
        }
        return max;
    }
}
