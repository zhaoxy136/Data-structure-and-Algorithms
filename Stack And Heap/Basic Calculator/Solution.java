//Version 0: original
public class Solution {
    public int calculate(String s) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();
        int val = 0;
        int num = 0;
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num = num * 10 + s.charAt(i) - '0';
            }
            if ((!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ') || i == s.length()-1) {
                if (s.charAt(i) == '(') {
                    stack1.push(val);
                    val = 0;
                    stack2.push(sign);
                    sign = '+';
                } else if (s.charAt(i) == ')') {
                    if (sign == '+') val += num;
                    else val -= num;
                    sign = stack2.pop();
                    if (sign == '+') val = stack1.pop()+val;
                    else val = stack1.pop()-val;
                } else {
                    if (sign == '+') val += num;
                    else val -= num;
                    sign = s.charAt(i);
                }
                num = 0;
            }
        }
        return val;
    }
}

