//Version 0: O(m*n)
public class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        for (int i = 0; i < haystack.length(); i++) {
            for (int j = 0; i + j < haystack.length(); j++) {
                if (haystack.charAt(i+j) != needle.charAt(j)) {
                    break;
                }
                if (j == needle.length()-1) {
                    return i;
                }
                if (i + j == haystack.length()-1) {
                    return -1;
                }
            }
        }
        return -1;
    }
}

//Version 1: O(m*n) bruteforce 
public class Solution {
    public int strStr(String haystack, String needle) {
        int i = 0;
        int j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == needle.length()) {
            return i - j;
        }
        return -1;
    }
}

