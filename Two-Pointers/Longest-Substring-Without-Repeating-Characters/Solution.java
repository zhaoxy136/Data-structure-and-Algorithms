//Version 0: sliding window, using hashmap
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int maxLen = 0;
        while (end < s.length()) {
            int tmp = map.getOrDefault(s.charAt(end), 0) + 1;
            map.put(s.charAt(end), tmp);
            while (map.get(s.charAt(end)) > 1) {
                map.put(s.charAt(start), map.get(s.charAt(start)) - 1);
                start++;
            }
            maxLen = Math.max(maxLen, end - start + 1);
            end++;
        }
        return maxLen;
    }
}

//Version 1: optimized 
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int len = 0;
        while (end < s.length()) {
            if (map.containsKey(s.charAt(end))) {
                start = Math.max(start, map.get(s.charAt(end))+1);
            }
            len = Math.max(len, end - start + 1);
            map.put(s.charAt(end), end);
            end++;
        }
        return len;
    }
}

//Version 2: using array instead of hashtable
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] index = new int[128];
        int max=0;
        for (int i=0, j=0; j < s.length(); j++){
            i = Math.max(i, index[s.charAt(j)]);
            max = Math.max(max, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return max;
    }
}
