public class Solution {
    public String countAndSay(int n) {
        if (n == 1) return "1";
        String last = countAndSay(n-1);
        StringBuilder sb = new StringBuilder();
        int count = 1;
        char num = last.charAt(0);
        for (int i = 1; i < last.length(); i++) {
            if (last.charAt(i) == num) count++;
            else {
                sb.append(count).append(num);
                count = 1;
                num = last.charAt(i);
            }
        }
        sb.append(count).append(num);
        return sb.toString();
    }
}
