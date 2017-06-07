//Version 0: original
public class Solution {
    public int romanToInt(String s) {
        int res = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'I') chars[i] = 'a';
            if (chars[i] == 'V') chars[i] = 'b';
            if (chars[i] == 'X') chars[i] = 'c';
            if (chars[i] == 'L') chars[i] = 'd';
            if (chars[i] == 'C') chars[i] = 'e';
            if (chars[i] == 'D') chars[i] = 'f';
            if (chars[i] == 'M') chars[i] = 'g';
        }
        int num = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'a') num = 1;
            if (chars[i] == 'b') num = 5;
            if (chars[i] == 'c') num = 10;
            if (chars[i] == 'd') num = 50;
            if (chars[i] == 'e') num = 100;
            if (chars[i] == 'f') num = 500;
            if (chars[i] == 'g') num = 1000;
            if (i < chars.length-1 && chars[i] < chars[i+1]) {
                res -= num;
            } else {
                res += num;
            }
        }
        return res;
    }
}
