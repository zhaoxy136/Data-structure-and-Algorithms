//Version 0:
public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) return res;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        int start = 0, end = 0, count = map.size();
        
        while (end < s.length()) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c)-1);
                if (map.get(c) == 0) count--;
            }
            end++;
            while (count == 0) {
                if (end - start == p.length()) res.add(start);
                char ch = s.charAt(start);
                if (map.containsKey(ch)) {
                    map.put(ch, map.get(ch)+1);
                    if (map.get(ch) > 0) count++;
                }
                start++;
            }
        }
        return res;
    }
}

//Version 1:
public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) return res;
        int[] map = new int[128];
        for (char c : p.toCharArray()) {
            map[c]++;
        }
        int start = 0, end = 0, count = p.length();
        while (end < s.length()) {
            char c = s.charAt(end++);
            if (map[c]-- > 0) count--;
            while (count == 0) {
                if (end - start == p.length()) res.add(start);
                char ch = s.charAt(start++);
                if (map[ch]++ == 0) count++;
            }
        }
        return res;
    }
}
