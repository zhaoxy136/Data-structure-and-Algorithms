class Solution {
    int[] orig;
    int[] arr;
    Random rand;
    public Solution(int[] nums) {
        orig = Arrays.copyOf(nums, nums.length);
        arr = nums;
        rand = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return orig;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        for (int i = 0; i < arr.length; i++) {
            swap(arr, i, rand.nextInt(i+1));
        }
        return arr;
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
