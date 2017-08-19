public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int k = primes.length;
        int[] index = new int[k];
        int[] factors = Arrays.copyOf(primes, k);
        int[] res = new int[n];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            for (int factor : factors) {
                res[i] = Math.min(res[i], factor);
            }
            for (int j = 0; j < k; j++) {
                if (res[i] == factors[j]) {
                    factors[j] = res[++index[j]] * primes[j];
                }
            }
        }
        return res[n-1];
    }
}
