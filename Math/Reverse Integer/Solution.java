//Version 0:
//learn how division and modulus work in programming language
public class Solution {
    public int reverse(int x) {
        int res = 0;
        int sign = x < 0 ? -1 : 1;
        while (x != 0) {
            int next = x % 10;
            if ((sign > 0 && (res > Integer.MAX_VALUE / 10 || res == Integer.MAX_VALUE / 10 && next > 7)) || (sign < 0 && (-res > Integer.MAX_VALUE / 10 || -res == Integer.MAX_VALUE / 10 && next > 8))) {
                return 0;
            } 
            res = res * 10 + next;
            x = x / 10;
        }
        return res;
    }
}
