//Version 0:
public class Solution {
    public int nextGreaterElement(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        //step: find first increasing element
        int i, j;
        for (i = chars.length-2; i >= 0; i--) {
            if (chars[i] < chars[i+1]) break;
        }
        if (i < 0) return -1;
        for (j = chars.length-1; j > i; j--) {
            if (chars[j] > chars[i]) break;
        }
        char ch = chars[i];
        chars[i++] = chars[j];
        chars[j] = ch;
        j = chars.length-1;
        while (i < j) {
            ch = chars[i];
            chars[i++] = chars[j];
            chars[j--] = ch;
        }
        String res = new String(chars);
        if (Integer.parseInt(res.substring(0,res.length()-1)) > Integer.MAX_VALUE/10 || Integer.parseInt(res.substring(0,res.length()-1)) == Integer.MAX_VALUE/10 && chars[chars.length-1] > '7') return -1;
        return Integer.parseInt(res);
    }
}
