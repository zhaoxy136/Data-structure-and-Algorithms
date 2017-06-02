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

//S1:better version
public class Solution {
    public String reverseStr(String s, int k) {
        char[] word = s.toCharArray();
        int i = 0;
        while (i < word.length) {
            int j = Math.min(word.length, i+k);
            reverse(word, i, j-1);
            i += 2*k;
        }
        return String.valueOf(word);
    }
    private void reverse(char[] word, int start, int end) {
        while (start < end) {
            char ch = word[start];
            word[start++] = word[end];
            word[end--] = ch;
        }
    }
}
