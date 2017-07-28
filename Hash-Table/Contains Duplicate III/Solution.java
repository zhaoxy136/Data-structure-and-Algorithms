//Version 0: TreeMap
public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0) return false;
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.ceiling((long)nums[i]) != null && set.ceiling((long)nums[i]) - nums[i] <= t) return true;
            if (set.floor((long)nums[i]) != null && (long)nums[i] - set.floor((long)nums[i]) <= t) return true;
            set.add((long)nums[i]);
            if (i >= k) set.remove((long)nums[i-k]);
        }
        return false;
    }
}

//Version 1: bucket sort
