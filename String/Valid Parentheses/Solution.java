//Version 0:original
public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[' || ch =='{') {
                stack.push(ch);
            } else {
                if (stack.isEmpty() || ch == ')' && stack.pop() != '(' || ch == ']' && stack.pop() != '[' || ch == '}' && stack.pop() != '{') return false;
                
            }
            
        }
        return stack.isEmpty();
    }
}

//Version: smarter
//@copyright https://discuss.leetcode.com/topic/27572/short-java-solution
public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(') stack.push(')');
            else if (ch == '[') stack.push(']');
            else if (ch == '{') stack.push('}');
            else if (stack.isEmpty() || ch != stack.pop()) return false;
        }
        return stack.isEmpty();
    }
}
