//Version 0 : original 
public class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int res = 0;
        int left = 0;
        while (left < height.length - 1) {
            while (left < height.length-1 && height[left] <= height[left+1]) {
                left++;
            }
            if (left == height.length - 1) break;
            int right = left+1;
            int max = 0;
            for (int i = left+1; i < height.length; i++) {
                if (height[i] > max) {
                    max = height[i];
                    right = i;
                }
                if (height[i] >= height[left]) break;
            }
            res += calculateVolume(height, left, right);
            left = right;
        }
        return res;
    }
    
    private int calculateVolume(int[] height, int left, int right) {
        
        System.out.println(left);
        System.out.println(right);
        int minHeight = Math.min(height[left], height[right]);
        int volume = 0;
        for (int i = left+1; i < right; i++) {
            volume += Math.max((minHeight - height[i]), 0);
        }
        System.out.println("vol" + volume);
        return volume;
    }
}

//Version 1: @copyright User: UpTheHell
/** Very classic Two Pointer question.
 *  If we want to trap water for one point i, we need to satisfy two conditions:
 *    1 leftBound > height[i]
 *    2 rightBound > height[i]
 *The water trapped = Min(leftBound, rightBound) - height[i]
 *So the key is always trap water from the side of lower bar.
 */
public class Solution {
    public int trap(int[] height) {
        int n = height.length;
        if (n < 3) {
            return 0;
        }
        int left = 0;
        int right = n-1;
        int sum = 0;
        int leftBound = height[0];
        int rightBound = height[n-1];
        while (left < right) {
            if (leftBound > rightBound) {
                if (height[--right] <= rightBound) {
                    sum += rightBound - height[right];
                } else {
                    rightBound = height[right];
                }
            } else {
                if (height[++left] <= leftBound) {
                    sum += leftBound - height[left];
                } else {
                    leftBound = height[left];
                }
            }
        }
        return sum;
    }
}

//Version 2: Using Stack
public class Solution {
    public int trap(int[] height) {
        int res = 0;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!st.isEmpty() && height[i] > height[st.peek()]) {
                int bot = height[st.pop()];
                if (!st.isEmpty()) {
                    int width = i - st.peek() -1;
                    int deep = Math.min(height[i], height[st.peek()]) - bot;
                    res += width * deep;
                }
            }
            st.push(i);
        }
        return res;
    }
}
