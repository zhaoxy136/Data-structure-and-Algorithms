public class Solution {
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int maxlen = 0;
        int dif = 0;
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            dif = nums[i] == 0 ? dif+1 : dif-1;
            //more brilliant 
            //dif -= 2 * nums[i] - 1;
            if (map.containsKey(dif)) {
                maxlen = Math.max(maxlen, i-map.get(dif));
            } else {
                map.put(dif, i);
            }
        }
        return maxlen;
    }
}
