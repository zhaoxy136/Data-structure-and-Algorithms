//Version 0:
public class Solution {
    public String addStrings(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = m - 1, j = n - 1; i >= 0 || j >= 0; --i, --j) {
            int tmp = carry;
            if (i >= 0) tmp += num1.charAt(i) - '0';
            if (j >= 0) tmp += num2.charAt(j) - '0';
            sb.insert(0, tmp % 10);
            carry = tmp / 10;
        }
        if (carry > 0) sb.insert(0, carry);
        return sb.toString();
        
    }
}
