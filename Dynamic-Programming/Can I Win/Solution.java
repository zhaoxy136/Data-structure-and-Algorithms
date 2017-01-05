//Version 0:
// Thanks to @https://discuss.leetcode.com/topic/68896/java-solution-using-hashmap-with-detailed-explanation/3
public class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) return true;
        int total = (maxChoosableInteger + 1) * maxChoosableInteger / 2;
        if (total < desiredTotal) return false;
        Map<String, Boolean> map = new HashMap<>();
        int[] used = new int[maxChoosableInteger];
        return helper(map, used, desiredTotal);
    }
    private boolean helper(Map<String, Boolean> map, int[] used, int totalLeft) {
        if (totalLeft <= 0) return false;
        String str = Arrays.toString(used);
        if (!map.containsKey(str)) {
            for (int i = 0; i < used.length; i++) {
                if (used[i] == 0) {
                    used[i] = 1;
                    if (!helper(map,used,totalLeft-(i+1))) {
                        map.put(str, true);
                        used[i] = 0;
                        return true;
                    }
                    used[i] = 0;
                }
            }
            map.put(str, false);
        }
        return map.get(str);
    }
}
