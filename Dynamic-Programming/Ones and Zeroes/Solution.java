//Version 0:
public class Solution {
    private class ResultType {
        int zeros;
        int ones;
        ResultType(int zeros, int ones) {
            this.zeros = zeros;
            this.ones = ones;
        }
    }
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) {
            return 0;
        }
        int[][] f = new int[m+1][n+1];
        for (int i = 1; i <= strs.length; i++) {
            ResultType tmp = findComponents(strs[i-1]);
            for (int j = m; j >= tmp.zeros; j--) {
                for (int k = n; k >= tmp.ones; k--) {
                    f[j][k] = Math.max(f[j][k], f[j-tmp.zeros][k-tmp.ones] + 1);
                }
            }
        }
        return f[m][n];
    }
    
    private ResultType findComponents(String str) {
        int zeros = 0;
        int ones = 0;
        for (char ch : str.toCharArray()) {
            if (ch == '0') zeros++;
            else ones++;
        }
        return new ResultType(zeros, ones);
    }
}

