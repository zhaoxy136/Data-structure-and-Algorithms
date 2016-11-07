public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        helper(res, new ArrayList<>(), nums, 0);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> subset, int[] nums, int start){
        res.add(new ArrayList<>(subset));
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]){
                continue;
            }
            subset.add(nums[i]);
            helper(res, subset, nums, i+1);
            subset.remove(subset.size()-1);
        }
    }
}
