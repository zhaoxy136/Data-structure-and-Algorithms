//Version 0:
public class Solution {
    public String addBinary(String a, String b) {
        char[] first = a.toCharArray();
        char[] second = b.toCharArray();
        int i = first.length-1, j = second.length-1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int x = i < 0 ? 0 : first[i--] - '0';
            int y = j < 0 ? 0 : second[j--] - '0';
            sb.insert(0, (x+y+carry)%2);
            carry = (x + y + carry) / 2;
        }
        if (carry > 0) sb.insert(0, carry);
        return sb.toString();
    }
}
