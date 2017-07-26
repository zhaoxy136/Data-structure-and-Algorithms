//Version 0: original
public class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;
        Stack<Integer> stack = new Stack<>();
        int prev = -1;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    prev = i;
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        maxLen = Math.max(maxLen, i-prev);
                    } else {
                        maxLen = Math.max(maxLen, i-stack.peek());
                    }
                }
            }
        }
        return maxLen;
    }
}

//Version 1:use dummy index instead of prev
public class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i-stack.peek());
                }
            }
        }
        return maxLen;
    }
}

//Version 2: DP
public class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;
        int[] dp = new int[s.length()+1];
        int max = 0;
        for (int i = 2; i <= s.length(); i++) {
            if (s.charAt(i-1) == ')') {
                if (s.charAt(i-2) == '(') {
                    dp[i] = dp[i-2] + 2;
                } else {
                    if (i-dp[i-1]-2 >= 0 && s.charAt(i-dp[i-1]-2) == '(') {
                        dp[i] = dp[i-dp[i-1]-2] + dp[i-1] + 2;
                    }
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
}

