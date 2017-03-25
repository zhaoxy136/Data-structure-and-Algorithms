//Version 0:
public class Solution {
    public List<Integer> grayCode(int n) {
        if (n == 0) return new ArrayList<Integer>(Arrays.asList(0));
        List<Integer> prev = grayCode(n-1);
        List<Integer> res = new ArrayList<>(prev);
        int add = 1 << (n-1);
        int len = prev.size();
        for (int i = len-1; i >= 0; i--) {
            res.add(prev.get(i)+add);
        }
        return res;
    }
}
