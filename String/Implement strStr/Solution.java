//Version 0:
public class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        if (haystack.length() < needle.length()) return -1;
        for (int i = 0; i <= haystack.length()-needle.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                int j = 0;
                while (j < needle.length() && haystack.charAt(i+j) == needle.charAt(j)) j++;
                if (j == needle.length()) return i;
            }
        }
        return -1;
    }
}

//Version 1:
//@copyright: https://discuss.leetcode.com/topic/18839/elegant-java-solution
public int strStr(String haystack, String needle) {
  for (int i = 0; ; i++) {
    for (int j = 0; ; j++) {
      if (j == needle.length()) return i;
      if (i + j == haystack.length()) return -1;
      if (needle.charAt(j) != haystack.charAt(i + j)) break;
    }
  }
}
