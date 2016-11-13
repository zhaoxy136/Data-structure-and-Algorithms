// Using getOrDefault()
public class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : magazine.toCharArray()){
            int tmp = map.getOrDefault(ch, 0) + 1;
            map.put(ch, tmp);
        }
        for (char ch : ransomNote.toCharArray()){
            int tmp = map.getOrDefault(ch, 0) - 1;
            if (tmp == -1){
                return false;
            }
            map.put(ch, tmp);
        }
        return true;
    }
}
