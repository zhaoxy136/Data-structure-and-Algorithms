//Version 0:
public class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i <= heights.length; i++) {
            int val = i == heights.length ? -1 : heights[i];
            while (!stack.isEmpty() && val <= heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = i;
                if (!stack.isEmpty()) {
                    width -= stack.peek() + 1;
                }
                res = Math.max(res, height * width);
            }
            stack.push(i);
        }
        return res;
    }
}
