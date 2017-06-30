//Version 0:
//@copyright: https://discuss.leetcode.com/topic/30508/easiest-java-solution-with-graph-explanation/13
public class Solution {
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] index = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                int tmp = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j;
                int p2 = i + j + 1;
                tmp += index[p2];
                index[p2] = tmp % 10;
                index[p1] += tmp / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i : index) {
            if (i != 0 || sb.length() > 0) {
                sb.append(i);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
