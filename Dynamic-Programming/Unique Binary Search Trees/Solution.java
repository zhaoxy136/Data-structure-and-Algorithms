public class Solution {
    public int numTrees(int n) {
        //cases[i] : first i elements can have how many cases
        int[] cases = new int[n+1];
        cases[0] = 1;
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 0; j < i; j++) {
                count += cases[j] * cases[i-j-1];
            }
            cases[i] = count;
        }
        return cases[n];
    }
}
