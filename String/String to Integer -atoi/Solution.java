//Version 0:
public class Solution {
    public int myAtoi(String str) {
        String digits = "0123456789";
        int i = 0;
        while (i < str.length() && str.charAt(i) == ' ') i++;
        if (i >= str.length()) return 0;
        int sign = 1;
        if (str.charAt(i) == '+' || str.charAt(i) =='-') {
            if (str.charAt(i++) == '-') sign = -1;
        }
        if (i >= str.length() || digits.indexOf(str.charAt(i)) == -1) return 0;
        int res = 0;
        while (i < str.length() && digits.indexOf(str.charAt(i)) != -1) {
            if (sign > 0 && (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (str.charAt(i) - '0') > Integer.MAX_VALUE % 10))) return Integer.MAX_VALUE;
            if (sign < 0 && (res > Integer.MIN_VALUE / -10 || (res == Integer.MIN_VALUE / -10 && (str.charAt(i) - '0') > -1 * (Integer.MIN_VALUE % 10)))) return Integer.MIN_VALUE;
            res = res * 10 + (str.charAt(i++) - '0');
        }
        return res * sign;
    }
}

//Version 0': elegant overflow process
public class Solution {
    public int myAtoi(String str) {
        String digits = "0123456789";
        int i = 0;
        while (i < str.length() && str.charAt(i) == ' ') i++;
        if (i >= str.length()) return 0;
        int sign = 1;
        if (str.charAt(i) == '+' || str.charAt(i) =='-') {
            if (str.charAt(i++) == '-') sign = -1;
        }
        if (i >= str.length() || digits.indexOf(str.charAt(i)) == -1) return 0;
        int res = 0;
        while (i < str.length() && digits.indexOf(str.charAt(i)) != -1) {
            if (res > Integer.MAX_VALUE / 10 || res == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > 7) {
                return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + (str.charAt(i++) - '0');
        }
        return res * sign;
    }
}
