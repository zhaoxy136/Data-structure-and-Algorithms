//Version 0: Vertical scanning
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int len = Integer.MAX_VALUE;
        for (String str : strs) {
            if (str.length() < len) {
                len = str.length();
            }
        }
        if (len == 0) return "";
        String res = "";
        for (int i = 0; i < len; i++) {
            char ch = strs[0].charAt(i);
            for (String str : strs) {
                if (str.charAt(i) != ch) return res;
            }
            res += ch;
        }
        return res;
    }
}
//Version 0'
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length(); i++) {
            char ch = strs[0].charAt(i);
            for (String str : strs) {
                if (i == str.length() || str.charAt(i) != ch) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}

//Version 1: Horizontal scanning
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String comp = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j;
            for (j = 0; j < Math.min(comp.length(), strs[i].length()); j++) {
                if (comp.charAt(j) != strs[i].charAt(j)) {
                    break;
                }
            }
            comp = comp.substring(0, j);
            if (comp == "") return comp;
        }
        return comp;
    }
}

//Version 1':
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String comp = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            while (j < comp.length() && j < strs[i].length() && comp.charAt(j) == strs[i].charAt(j)) {
                j++;
            }
            comp = comp.substring(0, j);
            if (comp == "") return comp;
        }
        return comp;
    }
}

//Version 2:Divide and Conquer
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        return helper(strs, 0, strs.length-1);
    }
    private String helper(String[] strs, int start, int end) {
        if (start == end) return strs[start];
        int mid = start + (end - start) / 2;
        String left = helper(strs, start, mid);
        String right = helper(strs, mid+1, end);
        int i = 0;
        while (i < left.length() && i < right.length()) {
            if (left.charAt(i) != right.charAt(i)) break;
            i++;
        }
        return left.substring(0, i);
    }
}

//Version 3:binary search
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int start = 0, end = strs[0].length()-1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (helper(strs, mid)) start = mid + 1;
            else end = mid - 1;
        }
        return strs[0].substring(0, start);
    }
    private boolean helper(String[] strs, int end) {
        for (int i = end; i >= 0; i--) {
            for (String str : strs) {
                if (i >= str.length() || str.charAt(i) != strs[0].charAt(i)) return false;
            }
        }
        return true;
    }
}


