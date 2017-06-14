public class Solution {
    public String complexNumberMultiply(String a, String b) {
        int[] x = getEle(a);
        int[] y = getEle(b);
        int real = x[0] * y[0] - x[1] * y[1];
        int imag = x[1] * y[0] + x[0] * y[1];
        return String.valueOf(real) + "+" + String.valueOf(imag) + "i";
    }
    private int[] getEle(String s) {
        int[] res = new int[2];
        int index = 0;
        int sign = 1;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int num = sign * (s.charAt(i) - '0');
                while (i+1 < s.length() && Character.isDigit(s.charAt(i+1))) {
                    num = num * 10 + sign * (s.charAt(i+1) - '0');
                    i++;
                }
                res[index++] = num;
                sign = 1;
            } else if (s.charAt(i) == '-') {
                sign = -1;
            }
        }
        return res;
    }
}
