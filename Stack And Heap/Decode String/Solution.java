//Version 0:
//understanding when to push,when to pop and what to store in the stack
public class Solution {
    public String decodeString(String s) {
        Stack<Integer> factor = new Stack<>();
        Stack<String> prevResult = new Stack<>();
        int count = 0;
        //StringBuilder res = new StringBuilder("");
        String res = "";
        int index = 0;
        while (index < s.length()) {
            char ch = s.charAt(index);
            if (ch - '0' >= 0 && ch - '0' <= 9) {
                count = count * 10 + ch - '0';
            } else if (ch == '[') {
                prevResult.push(res);
                factor.push(count);
                count = 0;
                //res = new StringBuilder("");
                res = "";
            } else if (ch == ']') {
                StringBuilder tmp = new StringBuilder(prevResult.pop());
                int prevFactor = factor.pop();
                while (prevFactor-- > 0) tmp.append(res);
                res = tmp.toString();
            } else {
                res += ch;
            }
            index++;
        }
        return res;
    }
}
