//Version 0:
public class Solution {
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        int[] next = new int[nums.length];
        Arrays.fill(next, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty()) {
                if (nums[i] < nums[stack.peek()]) break;
                next[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        int[] res = new int[findNums.length];
        for (int i = 0; i < findNums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == findNums[i]) {
                    res[i] = next[j];
                    break;
                }
            }
        }
        return res;
    }
}

//Version 1: bravo
public class Solution {
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            while (!stack.isEmpty() && stack.peek() < n) {
                map.put(stack.pop(), n);
            }
            stack.push(n);
        }
        int[] res = new int[findNums.length];
        for (int i = 0; i < findNums.length; i++) {
            res[i] = map.getOrDefault(findNums[i], -1);
        }
        return res;
    }
}
