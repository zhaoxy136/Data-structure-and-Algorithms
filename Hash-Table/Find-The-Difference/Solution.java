//Version 0:
public class Solution {
    public char findTheDifference(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            if(!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), 1);
            }else{
                int temp = map.get(s.charAt(i));
                map.put(s.charAt(i), temp+1);
            }
        }
        for(int j = 0; j < t.length(); j++){
            if(!map.containsKey(t.charAt(j))){
                return t.charAt(j);
            }
            int temp = map.get(t.charAt(j));
            if(temp == 0){
                return t.charAt(j);
            }
            map.put(t.charAt(j),temp-1);
        }
        return ' ';
    }
}

//Version 1: More Brilliant, thinking of 'find the missing number'
public class Solution {
    public char findTheDifference(String s, String t) {
        int charSumS = 0, charSumT = 0;
        for(int i = 0; i < s.length(); i++){
            charSumS += s.charAt(i);
        }
        for(int i = 0; i < t.length(); i++){
            charSumT += t.charAt(i);
        }
        return (char)(charSumT - charSumS);
    }
}

//Version 2: 
public class Solution {
    public char findTheDifference(String s, String t) {
        int res = (int)t.charAt(0);
        for(int i = 0; i < s.length(); ++i){
            res = res^(int)s.charAt(i);
            res = res^(int)t.charAt(i+1);
        }
        return (char)res;
    }
}
