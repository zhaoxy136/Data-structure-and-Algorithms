//Version 0: 60 ms solution, using charAt
public class Solution {
    public boolean isSubsequence(String s, String t) {
        if(s.length() == 0){
            return true;
        }
        int index = 0;
        int i = 0;
        while(i < t.length()){
            if(t.charAt(i) == s.charAt(index)){
                index++;
            }
            i++;
            if(index == s.length()){
                return true;
            }
        }
        return false;
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
