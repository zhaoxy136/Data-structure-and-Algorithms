public class Solution {
    public void sortColors(int[] nums) {
        int start = 0;
        for (int i = 1; i <= 2; i++){
            partition(nums, start, i);
        }
    }
    
    private void partition(int[] nums, int start, int pivot){
        if (start == nums.length){
            return;
        }
        int i = start;
        int j = nums.length - 1;
        while (i <= j){
            while (i <= j && nums[i] < pivot){
                i++;
            }
            while (i <= j && nums[j] >= pivot){
                j--;
            }
            if (i > j){
                start = i;
                return;
            }
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
