//Version 0: prefix product
public class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = Integer.MIN_VALUE;
        long minpos = 1;
        long maxneg = 1;
        long product = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                product = 1;
                minpos = 1;
                maxneg = 1;
                res = Math.max(res, 0);
            } else {
                product *= nums[i];
                if (product > 0) {
                    res = Math.max(res, (int)(product/minpos));
                    minpos = Math.min(minpos, product);
                }
                if (product < 0) {
                    res = Math.max(res, (int)(product/maxneg));
                    maxneg = maxneg > 0 ? product : Math.max(maxneg, product);
                }
            }
        }
        return res;
    }
}

//Version 1: original DP
public class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] localMax = new int[n];
        int[] localMin = new int[n];
        int[] global = new int[n];
        localMax[0] = nums[0];
        localMin[0] = nums[0];
        global[0] = nums[0];
        for (int i = 1; i < n; i++) {
            localMax[i] = Math.max(Math.max(localMax[i-1] * nums[i], localMin[i-1] * nums[i]), nums[i]);
            localMin[i] = Math.min(Math.min(localMax[i-1] * nums[i], localMin[i-1] * nums[i]), nums[i]);
            
            global[i] = Math.max(global[i-1], localMax[i]);
        }
        return global[n-1];
    }
}

//Version 2: modified
public class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int localMax = nums[0];
        int localMin = nums[0];
        int global = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int tmp = localMax;
            localMax = Math.max(Math.max(localMax * nums[i], localMin * nums[i]), nums[i]);
            localMin = Math.min(Math.min(tmp * nums[i], localMin * nums[i]), nums[i]);
            global = Math.max(global, localMax);
        }
        return global;
    }
}

//Version 3: more elegant
//@copyright https://discuss.leetcode.com/topic/4417/possibly-simplest-solution-with-o-n-time-complexity/2
int maxProduct(int A[], int n) {
    // store the result that is the max we have found so far
    int r = A[0];

    // imax/imin stores the max/min product of
    // subarray that ends with the current number A[i]
    for (int i = 1, imax = r, imin = r; i < n; i++) {
        // multiplied by a negative makes big number smaller, small number bigger
        // so we redefine the extremums by swapping them
        if (A[i] < 0)
            swap(imax, imin);

        // max/min product for the current number is either the current number itself
        // or the max/min by the previous number times the current one
        imax = max(A[i], imax * A[i]);
        imin = min(A[i], imin * A[i]);

        // the newly computed max value is a candidate for our global result
        r = max(r, imax);
    }
    return r;
}
