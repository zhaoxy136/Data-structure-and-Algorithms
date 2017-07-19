//Version 0: 40 ms solution, using charAt
public class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        int i = 0, j = 0;
        while (i < s.length()) {
            while (j < t.length() && t.charAt(j) != s.charAt(i)) j++;
            if (j++ >= t.length()) return false;
            i++;
        }
        return i == s.length();
        
    }
}

//Version 1: 2 ms solution, using indexOf
public class Solution {
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while(i < s.length()){
            if(t.indexOf(s.charAt(i),j) == -1){
                return false;
            } 
            j = t.indexOf(s.charAt(i),j) + 1;
            i++;
        }
        return i == s.length();
    }
}
