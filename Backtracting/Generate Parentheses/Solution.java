//Version 0:
public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(res, "", n, n);
        return res;
    }
    
    private void helper(List<String> res, String str, int open, int close) {
        if (open == 0 && close == 0) {
            res.add(str);
            return;
        }
        if (open > 0) {
            helper(res, str+"(", open-1, close);
        }
        if (open < close) {
            helper(res, str+")", open, close-1);
        }
    }
}
