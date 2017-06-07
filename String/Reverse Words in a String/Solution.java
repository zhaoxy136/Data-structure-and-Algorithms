//Version 0:
public class Solution {
    public String reverseWords(String s) {
        s = s.trim().replaceAll("\\s+", " ");
        char[] words = s.trim().toCharArray();
        int start = 0;
        for (int i = 0; i < words.length; i++) {
            while (i < words.length && words[i] != ' ') i++;
            reverse(words, start, i-1);
            start = ++i;
        }
        reverse(words, 0, words.length-1);
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

//Version 1:
