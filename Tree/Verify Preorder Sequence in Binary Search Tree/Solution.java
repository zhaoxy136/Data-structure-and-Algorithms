//Version 0:
class Solution {
    public boolean verifyPreorder(int[] preorder) {
        return helper(preorder, 0, preorder.length - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public boolean helper(int[] preorder, int start, int end, int min, int max) {
        if (start > end) return true;
        int root = preorder[start];
        if (root > max || root < min) return false;
        int i = start;
        while (i <= end && preorder[i] <= root) {
            i++;
        }
        return helper(preorder, start + 1, i - 1, min, root) && helper(preorder, i, end, root, max);
    }
}

//Version 1:
//@copyright:https://discuss.leetcode.com/topic/21217/java-o-n-and-o-1-extra-space
class Solution {
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) return true;
        Stack<Integer> st = new Stack<>();
        int low = Integer.MIN_VALUE;
        for (int p : preorder) {
            if (p < low) return false;
            while (!st.isEmpty() && p > st.peek()) {
                low = st.pop();
            }
            st.push(p);
        }
        return true;
    }
}
