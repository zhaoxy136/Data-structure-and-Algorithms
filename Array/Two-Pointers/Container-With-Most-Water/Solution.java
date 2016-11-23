public class Solution {
    public int maxArea(int[] height) {
        int n = height.length;
        if (n < 2) {
            return 0;
        }
        int left = 0;
        int right = n - 1;
        int sum = Math.min(height[0], height[n-1]) * (n - 1);
        while (left < right) {
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
            sum = Math.max(sum, (Math.min(height[left], height[right]) * (right - left)));
        }
        return sum;
    }
}
