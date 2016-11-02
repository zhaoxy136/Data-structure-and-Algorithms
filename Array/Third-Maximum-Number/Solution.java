//Version 0: Key point: use Long.MIN_VALUE to initialize to avoid the cases which the array include Integer.MIN_VALUE
public class Solution {
    public int thirdMax(int[] nums) {
        long max = Long.MIN_VALUE;
        long secondMax = Long.MIN_VALUE;
        long result = Long.MIN_VALUE;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > max){
                result = secondMax;
                secondMax = max;
                max = nums[i];
            }
            if(nums[i] > secondMax && nums[i] < max){
                result = secondMax;
                secondMax = nums[i];
            }
            if(nums[i] > result && nums[i] < secondMax){
                result = nums[i];
            }
            
        }
        return (int)(result == Long.MIN_VALUE? max : result);
    }
}

//Version 1: //Using TreeSet Class
public class Solution {
    public int thirdMax(int[] nums) {
        TreeSet<Integer> top3 = new TreeSet<>();
        for(int num: nums){
            top3.add(num);
            if(top3.size() > 3){
                top3.pollFirst();
            }
        }
        return top3.size() == 3 ? top3.pollFirst() : top3.pollLast();
    }
}
