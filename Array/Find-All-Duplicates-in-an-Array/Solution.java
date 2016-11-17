public class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++){
            int index = (nums[i] - 1) % n;
            nums[index] = nums[index] + n;
        }
        for (int i = 0; i < n; i++){
            if (nums[i] > 2 * n){
                res.add(i+1);
            }
        }
        return res;
    }
}
