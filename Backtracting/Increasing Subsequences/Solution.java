//Version 0:
public class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), nums, 0);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int index) {
        //if (index == nums.length) return;
        if (list.size() > 1) res.add(new ArrayList<>(list));
        Set<Integer> set = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (!list.isEmpty() && nums[i] < list.get(list.size()-1) || set.contains(nums[i])) continue;
            list.add(nums[i]);
            set.add(nums[i]);
            helper(res, list, nums, i+1);
            list.remove(list.size()-1);
        }
    }
}
