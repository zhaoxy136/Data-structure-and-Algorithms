//Version 0:
public class Solution {
    public String reverseWords(String s) {
        char[] words = s.toCharArray();
        int start = 0;
        for (int i = 0 ; i < words.length; i++) {
            while (i < words.length && words[i] != ' ') {
                i++;
            }
            reverse(words, start, i-1);
            start = ++i;
        }
        return String.valueOf(words);
    }
    private void reverse(char[] words, int start, int end) {
        while (start < end) {
            char ch = words[start];
            words[start++] = words[end];
            words[end--] = ch;
        }
    }
}
