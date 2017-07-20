//Version 0:
public class Solution {
    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = "" + nums[i];
        }
        Arrays.sort(strs, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2) {
                for (int i = 0; i < s1.length() + s2.length(); i++) {
                    int tmp = s2.charAt(i%s2.length()) - s1.charAt(i%s1.length());
                    if (tmp != 0) {
                        return tmp;
                    }
                }
                return 0;
            }
        });
        if (strs[0].charAt(0) == '0') return "0";
        String res = "";
        for (String str : strs) {
            res += str;
        }
        return res;
    }
}

