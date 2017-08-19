class Solution {
    public int nthUglyNumber(int n) {
        int[] res = new int[n];
        res[0] = 1;
        int factor1 = 2, factor2 = 3, factor3 = 5;
        int index1 = 1, index2 = 1, index3 = 1;
        for (int i = 1; i < n; i++) {
            res[i] = Math.min(Math.min(factor1, factor2), factor3);
            if (res[i] == factor1) {
                factor1 = res[index1++] * 2;
            }
            if (res[i] == factor2) {
                factor2 = res[index2++] * 3;
            }
            if (res[i] == factor3) {
                factor3 = res[index3++] * 5;
            }
        }
        return res[n-1];
    }
}
