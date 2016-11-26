//Version 0: O(n2)
public class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        }
        if (needle.length() == 0) {
            return 0;
        }
        int res = -1;
        int start = 0;
        int end = 0;
        while (end < haystack.length()) {
            while (end < haystack.length() && haystack.charAt(end) != needle.charAt(start)) {
                end++;
            }
            res = end;
            while (start < needle.length() && end < haystack.length()) {
                if (needle.charAt(start) != haystack.charAt(end)) {
                    break;
                }
                start++;
                end++;
            }
            if (start == needle.length()) {
                return res;
            }
            if (end == haystack.length()) {
                return -1;
            }
            start = 0;
            end = res + 1;
        }
        return -1;
    }
}

//Version 1: O(n2)
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
