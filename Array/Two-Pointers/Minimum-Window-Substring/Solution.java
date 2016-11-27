//Version 0:
public class Solution {
    public String minWindow(String s, String t) {
        if (s.length() == 0) return "";
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            if (map.containsKey(t.charAt(i))) {
                map.put(t.charAt(i), map.get(t.charAt(i)) + 1);
            } else {
                map.put(t.charAt(i), 1);
            }
        }
        int left = 0;
        int right = 0;
        int minLeft = 0;
        int len = Integer.MAX_VALUE;
        int count = 0;
        while (right < s.length()) {
            if (map.containsKey(s.charAt(right))) {
                map.put(s.charAt(right), map.get(s.charAt(right)) - 1);
                if (map.get(s.charAt(right)) >= 0) {
                    count++;
                }
                while (count == t.length()) {
                    if (right - left + 1 < len) {
                        minLeft = left;
                        len = right - left + 1;
                    }
                    if (map.containsKey(s.charAt(left))) {
                        map.put(s.charAt(left), map.get(s.charAt(left)) + 1);
                        if (map.get(s.charAt(left)) > 0){
                            count--;
                        }
                    }
                    left++;
                }
            }
            right++;
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + len);
    }
}

//Version 1: use array instead of hashmap
// @copyright https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems
public class Solution {
    public String minWindow(String s, String t) {
        int[] chars = new int[128];
        for (char c : t.toCharArray()) {
            chars[c]++;
        }
        int left = 0;
        int right = 0;
        int len = Integer.MAX_VALUE;
        int count = t.length();
        int begin = 0;
        while (right < s.length()) {
            if(chars[s.charAt(right++)]-- > 0) {
                count--;
                while (count == 0) {
                    if (right - left < len) {
                        begin = left;
                        len = right - left;
                    }
                    if (chars[s.charAt(left++)]++ == 0) {
                        count++;
                    }
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(begin, begin + len);
    }
}
