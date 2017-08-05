public class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] map = new int[128];
        int start = 0, end = 0;
        int count = 0;
        int len = 0;
        while (end < s.length()) {
            if (map[s.charAt(end++)]++ == 0) count++;
            while (count > k) {
                if (map[s.charAt(start++)]-- == 1) count--;
            }
            len = Math.max(len, end-start);
        }
        return len;
    }
}
