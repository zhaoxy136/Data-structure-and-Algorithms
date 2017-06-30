//Version 0:
public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        if (nums == null || nums.length == 0 || k == 0) return new int[0];
        Deque<Integer> deck = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!deck.isEmpty() && nums[i] > deck.peekLast()) {
                deck.pollLast();
            }
            deck.add(nums[i]);
            if (i + 1 > k && deck.peekFirst() == nums[i-k]) {
                deck.pollFirst();
            }
            if (i + 1 >= k) {
                res[i+1-k] = deck.peekFirst();
            }
        }
        return res;
    }
}
