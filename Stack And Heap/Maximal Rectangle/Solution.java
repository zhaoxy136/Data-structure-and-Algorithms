public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int[] heights = new int[matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            heights[i] = matrix[0][i] - '0';
        }
        int res = maxInline(heights);
        for (int i = 1; i < matrix.length; i++) {
            updateHeight(matrix, heights, i);
            res = Math.max(res, maxInline(heights));
        }
        return res;
    }
    private void updateHeight(char[][] matrix, int[] heights, int i) {
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[i][j] == '1') heights[j] += 1;
            else heights[j] = 0;
        }
    }
    private int maxInline(int[] heights) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= heights.length; i++) {
            int tmp = i == heights.length ? -1 : heights[i];
            while (!stack.isEmpty() && tmp < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = i - (stack.isEmpty() ? 0 : stack.peek()+1);
                res = Math.max(res, height * width);
            }
            stack.push(i);
        }
        return res;
    }
}
