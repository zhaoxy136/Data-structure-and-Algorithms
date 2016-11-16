//original
class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length == 0){
            return;
        }
        int start = 0;
        for (int i = 1; i <= k; i++){
            partition(colors, start, i);
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
