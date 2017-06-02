//S0:
public class Solution {
    public String reverseStr(String s, int k) {
        if (s == null || s.length() == 0) return s;
        int i = 0;
        while (i < s.length()) {
            if (s.length() - i <= k) {
                s = reverse(s, i, s.length()-1);
                i = s.length();
            } else {
                s = reverse(s, i, i+k-1);
                i += 2*k;
            }
        }
        return s;
    }
    private String reverse(String s, int start, int end) {
        char[] word = s.toCharArray();
        while (start < end) {
            char ch = word[start];
            word[start++] = word[end];
            word[end--] = ch;
        }
        return String.valueOf(word);
    }
}

