//Version 0: 
public class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return -1;
        Map<Character, Integer> map = new HashMap<>();
        int[] arr = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                arr[i] = 2;
                arr[map.get(s.charAt(i))]++;
            } else {
                arr[i] = 1;
                map.put(s.charAt(i), i);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) return i;
        }
        return -1;
    }
}

//Version 1: Two pass O(n)
public class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return -1;
        int[] letters = new int[26];
        for (int i = 0; i < s.length(); i++) {
            letters[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (letters[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
