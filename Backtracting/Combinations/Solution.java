public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), n, 1, k);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> combination, int n, int start, int k){
        if(k == 0){
            res.add(new ArrayList<>(combination));
            return;
        }
        
        for(int i = start; i <= n; i++){
            combination.add(i);
            helper(res, combination, n, i+1, k-1);
            combination.remove(combination.size()-1);
        }
        
    }
}
