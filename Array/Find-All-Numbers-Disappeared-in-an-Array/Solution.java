//Version 0: original
public class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 1; i <= n; i++){
            res.add(i);
        }
        for (int i = 0; i < n; i++){
            res.remove(new Integer(nums[i]));
        }
        return res;
    }
}

//Version 1: more elegant solution
/** iterate the array, once we encounter a number, we change the element in the corresponding index to negative,
 *after that, we just need to find which index position remains positive.
 */
public class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++){
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0){
                nums[index] = -nums[index];
            }
        }
        for (int i = 0; i < n; i++){
            if (nums[i] > 0){
                res.add(i+1);
            }
        }
        return res;
    }
}
