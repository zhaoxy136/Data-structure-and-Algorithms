//Version 0:
public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        int[] nums = new int[9];
        for(int i = 0; i < 9; i++){
            nums[i] = i+1;
        }
        helper(res, new ArrayList<>(), nums, k, n, 0);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> com, int[] nums, int k, int target, int start){
        if(k == 0 && target == 0){
            res.add(new ArrayList<>(com));
            return;
        }
        if(k != 0 && target > 0){
            for(int i = start; i < nums.length; i++){
                com.add(nums[i]);
                helper(res, com, nums, k-1, target-nums[i], i+1);
                com.remove(com.size()-1);
            }
        }
    }
}

//Version 1: updated 02.24
public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        //from 1 to 9,  the maximum sum is 45
        if (k <= 0 || n > 45) return res;
        helper(res, new ArrayList<>(), k, n, 1);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> combination, int k, int n, int start) {
        if (combination.size() >= k) {
            if (n == 0) res.add(new ArrayList<>(combination));
            return;
        }
        for (int i = start; i < 10; i++) {
            if (i > n) return;
            combination.add(i);
            helper(res, combination, k, n-i, i+1);
            combination.remove(combination.size()-1);
        }
    }
}
