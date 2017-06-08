//Version 0:
public class Solution {
    public int countSegments(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                count++;
                while (i < s.length() && s.charAt(i) != ' ') i++;
            }
        }
        return count;
    }
}
