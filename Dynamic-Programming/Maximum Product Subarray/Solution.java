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

