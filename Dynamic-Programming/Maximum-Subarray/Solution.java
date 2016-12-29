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

//Version 2: have to know how to use DP
public class Solution {
    public int maxSubArray(ArrayList<Integer> nums) {
        if(nums.size()==0)  
            return 0;  
        int n = nums.size();
        int []global = new int[n]; //global[i]: maximal sum within first i elements
        int []local = new int[n]; //local[i]: maximal sum ended with the ith element
        global[0] = nums.get(0);
        local[0] = nums.get(0);
        for(int i=1;i<n;i++)  
        {  
            local[i] = Math.max(nums.get(i),local[i-1]+nums.get(i));  
            global[i] = Math.max(local[i],global[i-1]);  
        }  
        return global[n-1];  
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
