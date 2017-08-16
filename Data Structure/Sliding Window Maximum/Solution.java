//Version 0:
public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        int[] res = new int[nums.length + 1 - k];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && queue.peekFirst() < nums[i]) {
                queue.pop();
            }
            queue.push(nums[i]);
            if (i >= k - 1) {
                res[i+1-k] = queue.peekLast();
                if (queue.peekLast() == nums[i+1-k])
                    queue.pollLast();
            }
            
        }
        return res;
    }
}
