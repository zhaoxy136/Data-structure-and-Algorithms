//Version 0:
public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        if (digits == null || digits.length() == 0){
            return res;
        }
        HashMap<Character,String> map = new HashMap<Character,String>();
        String[] stringList = preparation(digits, map);
        helper(res, stringList, str, 0);
        return res;
        
    }
    
    private void helper(List<String> res, String[] stringList, StringBuilder str, int index){
        if (index == stringList.length){
            res.add(new String(str));
            return;
        }
        
        String tmp = stringList[index];
        for (int i = 0; i < tmp.length(); i++){
            str.append(tmp.charAt(i));
            helper(res, stringList, str, index+1);
            str.deleteCharAt(str.length()-1);
        }
    }
    
    private String[] preparation(String digits, HashMap<Character, String> map){
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        String[] stringList = new String[digits.length()];
        for (int i = 0; i < digits.length(); i++){
            if (map.containsKey(digits.charAt(i))){
                stringList[i] = map.get(digits.charAt(i));
                //stringList.add(map.get(digits.charAt(i)));
            }
        }
        return stringList;
    }
}

//Version 1:
public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        for (char ch : digits.toCharArray()) {
            if (ch - '0' < 2 || ch - '0' > 9) {
                return res;
            }
        }
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        
        helper(res, map, digits, "", 0);
        return res;
    }
    
    private void helper(List<String> res, Map<Character, String> map, String digits, String comb, int index) {
        if (index == digits.length()) {
            res.add(""+comb);
            return;
        }
        char[] list = map.get(digits.charAt(index)).toCharArray();
        for (char ch : list) {
            helper(res, map, digits, comb+ch, index+1);
        }
    }
}
