//Version 0:
public class Solution {
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) return false;
        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(min[i-1], nums[i]);
        }
        Stack<Integer> stack = new Stack<>();
        for (int j = nums.length-1; j > 0; j--) {
            int max = Integer.MIN_VALUE;
            while (!stack.isEmpty() && nums[j] > stack.peek()) {
                max = stack.pop();
            }
            if (max > min[j-1]) return true;
            stack.push(nums[j]);
        }
        return false;
        
    }
}

//Version 1:
//use s3 to maintain the largest value has a greater element before it,when nums[i] < s3, nums[i] can be regarded as s1.
public class Solution {
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) return false;
        int s3 = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < s3) return true;
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                s3 = Math.max(s3, stack.pop());
            }
            stack.push(nums[i]);
        }
        return false;
    }
}

