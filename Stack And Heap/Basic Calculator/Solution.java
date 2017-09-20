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

//Version 1:one stack
public class Solution {
    public int calculate(String s) {
        int sign = 1, res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int num = s.charAt(i) - '0';
                while (i+1 < s.length() && Character.isDigit(s.charAt(i+1))) { //i+1 check next char
                    num = num * 10 + s.charAt(i+1) - '0';
                    i++;
                }
                res += sign * num;
            } else if(s.charAt(i) == '+') {
                sign = 1;
            } else if (s.charAt(i) == '-') {
                sign = -1;
            } else if (s.charAt(i) == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                res = res * stack.pop() + stack.pop();
            }
        }
        return res;
    }
}

//Version 2:
//example "1 4 +1", in version 0 and 1,return 6, in version 2 return 15, which I think is more accurate.
class Solution {
    public int calculate(String s) {
        char[] str = s.toCharArray();
        Stack<Integer> st = new Stack<>();
        int sign = 1;
        int res = 0;
        int num = 0;
        for (int i = 0; i < str.length; i++) {
            if (Character.isDigit(str[i])) {
                num = num * 10 + str[i] - '0';
            } else if (str[i] != ' ' && !Character.isDigit(str[i])) {
                if (str[i] == '+' || str[i] == '-') {
                    res += sign * num;
                    sign = str[i] == '+' ? 1 : -1;
                } else if (str[i] == '(') {
                    st.push(sign);
                    st.push(res);
                    res = 0;
                    sign = 1;
                } else {
                    res += sign * num;
                    res = st.pop() + st.peek() * res;
                    sign = st.pop();
                }
                num = 0;
            }
        }
        res += sign * num;
        return res;
    }
}
