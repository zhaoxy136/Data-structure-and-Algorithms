//Version 0: original but slow
//since when n is large, the largest factor int the set could be overflow, I used a double set
public class Solution {
    public int nthUglyNumber(int n) {
        int[] factor = new int[]{2,3,5};
        TreeSet<Double> set = new TreeSet<>();
        set.add(1.0);
        double res = 1.0;
        while(n-- > 0) {
            res = set.pollFirst();
            for (int c : factor) {
                set.add(c * res);
            }
        }
        return (int)res;
    }
}

//Version 1: 
public class Solution {
    public int nthUglyNumber(int n) {
        int[] res = new int[n];
        res[0] = 1;
        int[] factor = new int[]{2,3,5};
        int index1 = 0, index2 = 0, index3 = 0;
        for (int i = 1; i < n; i++) {
            res[i] = Math.min(Math.min(factor[0], factor[1]), factor[2]);
            if (res[i] == factor[0]) {
                factor[0] = res[++index1] * 2;
            }
            if (res[i] == factor[1]) {
                factor[1] = res[++index2] * 3;
            }
            if (res[i] == factor[2]) {
                factor[2] = res[++index3] * 5;
            }
        }
        return res[n-1];
    }
}
