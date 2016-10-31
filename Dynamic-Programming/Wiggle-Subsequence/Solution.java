public class Solution {
    public int wiggleMaxLength(int[] nums) {
        if(nums.length < 2){
            return nums.length;
        }
        int[] diffArr = new int[nums.length-1]; //store the difference between two consecutive elements
        for(int i = 1; i < nums.length; i++){
            diffArr[i-1] = nums[i] - nums[i-1];
        }
        int temp = diffArr[0];
        int length = diffArr[0] == 0? 1:2;
        for(int i = 1; i < diffArr.length; i++){
            if(diffArr[i] != 0){
                if((diffArr[i] ^ temp) >>> 31 == 1 || temp == 0){ //determine if they are opposite
                    length++;
                }
                temp = diffArr[i];
            }
        }
        return length;
    }
}
