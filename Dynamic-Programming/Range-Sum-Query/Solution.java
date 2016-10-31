public class NumArray {
    //numSum[i] = the sum value of first i elements
    private int[] numSum;
    public NumArray(int[] nums) {
        this.numSum = new int[nums.length + 1];
        numSum[0] = 0;
        for(int i = 1; i <= nums.length; i++){
            numSum[i] = numSum[i-1] + nums[i-1]; 
        }
    }

    public int sumRange(int i, int j) {
        return numSum[j+1] - numSum[i]; 
    }
}
