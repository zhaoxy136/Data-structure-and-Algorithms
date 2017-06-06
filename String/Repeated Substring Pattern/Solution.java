//Version 0: original
public class Solution {
    public boolean repeatedSubstringPattern(String s) {
        for (int len = 1; len <= s.length()/2; len++) {
            if (s.length() % len != 0) continue;
            int i = 0;
            while (i < s.length()) {
                if (s.charAt(i) != s.charAt(i%len)) break;
                i++;
            }
            if (i == s.length()) return true;
        }
        return false;
    }
}

//Version 1:

