//Version 0 : original
// 1. always think about how to reduce the scale with O(1) operation.
// 2. remember to concider the corner case and handle the overflow.
public class Solution {
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n < 0) {
            n = -1 * (n + 1);
            return 1 / (x * myPow(x, n));
        }
        if (n == 1) return x;
        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x, n - 1);
    }
}

