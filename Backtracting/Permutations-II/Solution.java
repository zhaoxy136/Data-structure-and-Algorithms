//Version 0: 
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        helper(res, new ArrayList<>(), new ArrayList<>(), nums);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> permute, List<Integer> index, int[] nums){
        if(permute.size() == nums.length){
            res.add(new ArrayList<>(permute));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(index.contains(i)){
                continue;
            }
            if(i > 0 && nums[i] == nums[i-1] && !index.contains(i-1)){
                continue;
            }
            permute.add(nums[i]);
            index.add(i);
            helper(res, permute, index, nums);
            permute.remove(permute.size()-1);
            index.remove(index.size()-1);
        }
    }
}

