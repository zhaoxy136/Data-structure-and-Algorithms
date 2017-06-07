//Version 0:
public class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;
        int[] chars = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            chars[magazine.charAt(i)-'a']++;
        }
        for (int j = 0; j < ransomNote.length(); j++) {
            if (--chars[ransomNote.charAt(j)-'a'] < 0) return false;
        }
        return true;
    }
}
