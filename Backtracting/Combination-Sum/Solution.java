//Version 0:
/**
 * used three extra auxiliary variables
 * res to store all valid solutions
 * solution to store current selection
 * start represents current position
 */
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        helper(res, solution, candidates, target, 0);
        return res;
    }
    // solve all valid solutions and store them in res
    private void helper(List<List<Integer>> res, List<Integer> solution, int[] nums, int target, int start){
        if(target < 0 || start >= nums.length){
            return;
        }
        if(target == 0){
            res.add(new ArrayList<>(solution));
            return;
        }
        
        for(int i = start; i < nums.length; i++){
            solution.add(nums[i]);
            helper(res, solution, nums, target-nums[i], i);
            solution.remove(solution.size()-1);
        }
    }
}


