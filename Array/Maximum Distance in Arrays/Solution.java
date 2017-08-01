//Version 0: original
public class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        int invalidMax = 0;
        for (List<Integer> list : arrays) {
            int tmp = list.get(0);
            if (tmp < min1) {
                min2 = min1;
                min1 = tmp;
                invalidMax = list.get(list.size()-1);
            } else if (tmp < min2) {
                min2 = tmp;
            }
            tmp = list.get(list.size()-1);
            if (tmp > max1) {
                max2 = max1;
                max1 = tmp;
            } else if (tmp > max2) {
                max2 = tmp;
            }
        }
        if (max1 != invalidMax) return max1 - min1;
        return Math.max((max1 - min2), (max2 - min1));
    }
}

//Version 1: more elegant, one pass
public class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int res = 0, min = arrays.get(0).get(0), max = arrays.get(0).get(arrays.get(0).size()-1);
        for (int i = 1; i < arrays.size(); i++) {
            res = Math.max(res, Math.abs(max - arrays.get(i).get(0)));
            res = Math.max(res, Math.abs(min - arrays.get(i).get(arrays.get(i).size() - 1)));
            max = Math.max(max, arrays.get(i).get(arrays.get(i).size() - 1));
            min = Math.min(min, arrays.get(i).get(0));
        }
        return res;
    }
}
