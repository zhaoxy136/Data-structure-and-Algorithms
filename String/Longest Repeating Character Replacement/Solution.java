//Version 0: since the converted substring could only contains one character, we can check all possibles from A to Z
//Time complexity O(26N)
public class Solution {
    public int characterReplacement(String s, int k) {
        int res = 0;
        for (int i = 0; i < 26; i++) {
            int start = 0, end = 0;
            int count = 0;
            while (end < s.length()) {
                if (s.charAt(end++) - 'A' != i) count++;
                while (count > k) {
                    if (s.charAt(start++) - 'A' != i) count--;
                }
                res = Math.max(res, end - start);
            }
        }
        return res;
    }
}

//Version 1:
public class Solution {
    public int characterReplacement(String s, int k) {
        int[] map = new int[26];
        int start = 0, end = 0;
        int res = 0, maxCount = 0;
        while (end < s.length()) {
            map[s.charAt(end) - 'A']++;
            maxCount = Math.max(maxCount, map[s.charAt(end++) - 'A']);
            while (end-start-maxCount > k) {
                map[s.charAt(start++) - 'A']--;
                maxCount = 0;
                for (int i = 0; i < 26; i++) {
                    maxCount = Math.max(maxCount, map[i]);
                }
            }
            res = Math.max(res, end-start);
        }
        return res;
    }
}
