//classic solution
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> permute = new ArrayList<>();
        helper(res, permute, nums);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> permute, int[] nums){
        if(permute.size() == nums.length){
            res.add(new ArrayList<>(permute));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(permute.contains(nums[i])){
                continue;
            }
            permute.add(nums[i]);
            helper(res, permute, nums);
            permute.remove(permute.size()-1);
        }
    }
}
