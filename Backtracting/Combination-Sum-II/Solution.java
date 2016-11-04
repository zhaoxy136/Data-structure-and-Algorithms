//The point is how to avoid duplicate solutions
public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        Arrays.sort(candidates);
        helper(res, solution, candidates, target, 0);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> solution, int[] nums, int target, int start){
        if(target < 0 || start > nums.length){
            return;
        }
        if(target == 0){
            res.add(new ArrayList<>(solution));
            return;
        }
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]){
                continue;
            }
            solution.add(nums[i]);
            helper(res, solution, nums, target-nums[i], i+1);
            solution.remove(solution.size()-1);
        }
    }
}
