// Version 0:
class Solution {
    int sum = 0;
    Map<Integer, Integer> map = new HashMap<>();
    public int pathSum(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        for (int n : nums) {
            int key = n / 10;
            int val = n % 10;
            map.put(key, val);
        }
        helper(nums[0] / 10, 0);
        return sum;
    }
    
    public void helper(int pos, int preSum) {
        int level = pos / 10;
        int order = pos % 10;
        
        int left = (level + 1) * 10 + order * 2 - 1;
        int right = (level + 1) * 10 + order * 2;
        
        preSum += map.get(pos);
        if (!map.containsKey(left) && !map.containsKey(right)) {
            sum += preSum;
            return;
        }
        if (map.containsKey(left)) {
            helper(left, preSum);
        }
        if (map.containsKey(right)) {
            helper(right, preSum);
        }
    }
}

//Version 1: no global variables
class Solution {
    public int pathSum(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            int key = n / 10;
            int val = n % 10;
            map.put(key, val);
        }
        return helper(map, nums[0] / 10, 0);
    }
    
    public int helper(Map<Integer, Integer> map, int pos, int preSum) {
        if (!map.containsKey(pos)) return 0;
        
        preSum += map.get(pos);
        
        int level = pos / 10;
        int order = pos % 10;
        int left = (level + 1) * 10 + order * 2 - 1;
        int right = (level + 1) * 10 + order * 2;
        
        if (!map.containsKey(left) && !map.containsKey(right)) {
            return preSum;
        }
        return helper(map, left, preSum) + helper(map, right, preSum);
    }
}
