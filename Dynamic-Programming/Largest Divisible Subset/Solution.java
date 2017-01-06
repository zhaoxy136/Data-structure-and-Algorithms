//Version 0: 
public class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        //f[i] : the largest length of subset ended with the ith element
        int[] f = new int[nums.length];
        f[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            f[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && f[j] + 1 > f[i]) {
                    f[i] = f[j] + 1;
                    map.put(i, j);
                }
            }
        }
        int lastPosition = 0;
        for (int i = 1; i < nums.length; i++) {
            if (f[i] > f[lastPosition]) lastPosition = i;
        }
        return print(nums, map, lastPosition);
    }
    private List<Integer> print(int[] nums, Map<Integer, Integer> map, int pos) {
        List<Integer> list = new ArrayList<>();
        list.add(nums[pos]);
        while (map.containsKey(pos)) {
            pos = map.get(pos);
            list.add(nums[pos]);
        }
        return list;
    }
}

//Version 1: 
//same idea, but more efficient
//@copyright https://discuss.leetcode.com/topic/49652/classic-dp-solution-similar-to-lis-o-n-2
public class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        int[] f = new int[nums.length];
        int[] prev = new int[nums.length];
        int lastPos = 0;
        for (int i = 0; i < nums.length; i++) {
            f[i] = 1;
            prev[i] = -1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && f[j] + 1 > f[i]) {
                    f[i] = f[j] + 1;
                    prev[i] = j;
                }
            }
            if (f[i] > f[lastPos]) lastPos = i;
        }
        res.add(nums[lastPos]);
        while (prev[lastPos] != -1) {
            lastPos = prev[lastPos];
            res.add(nums[lastPos]);
        }
        return res;
    }
}
