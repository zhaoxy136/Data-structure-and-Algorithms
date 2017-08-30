//Version 0: using hashmap
class Solution {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                int left = map.containsKey(num-1) ? map.get(num-1) : 0;
                int right = map.containsKey(num+1) ? map.get(num+1) : 0;
                int len = left + right + 1;
                res = Math.max(res, len);
                
                map.put(num, len);
                map.put(num - left, len);
                map.put(num + right, len);
            }
        }
        return res;
    }
}

//Version 1:using hashset
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int res = 0;
        for (int n : nums) {
            set.add(n);
        }
        for (int n : nums) {
            if (set.remove(n)) {
                int val = n;
                int len = 1;
                while (set.remove(val-1)) {
                    val--;
                    len++;
                }
                while (set.remove(n+1)) {
                    n++;
                    len++;
                }
                res = Math.max(res, len);
            }
        }
        return res;
    }
}
