//Version 0: 
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), nums, 0, nums.length-1);
        return res;
    }
    
    private void helper(List<List<Integer>>res, List<Integer> subset, int[] nums, int start, int end){
        res.add(new ArrayList<>(subset));
        if(start > end){
            return;
        }
        for(int i = start; i < nums.length; i++){
            subset.add(nums[i]);
            helper(res, subset, nums, i+1, end);
            subset.remove(subset.size()-1);
        }
    }
}
