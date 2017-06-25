//Version 0:
public class Solution {
    public String convert(String s, int n) {
        if (n == 1) return s;
        StringBuilder sb = new StringBuilder();
        int step = 2 * n - 2;
        for (int i = 0; i < n; i++) {
            int j = i;
            while (j < s.length()) {
                sb.append(s.charAt(j));
                j += step;
                if (2 * i > 0 && 2 * i < step && j - 2 * i < s.length()) {
                    sb.append(s.charAt(j - 2 * i));
                }
            }
            
        }
        return sb.toString();
    }
}
