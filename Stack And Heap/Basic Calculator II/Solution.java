//Version 0:
public class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num = num * 10 + s.charAt(i) - '0';
            }
            if ((s.charAt(i) != ' ' && (s.charAt(i) < '0' || s.charAt(i) > '9')) || i == s.length()-1) {
                if (sign == '+') {
                    stack.push(num);
                }
                if (sign == '-') {
                    stack.push(-1 * num);
                }
                if (sign == '*') {
                    stack.push(stack.pop() * num);
                }
                if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
