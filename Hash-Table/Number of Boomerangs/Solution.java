public class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            int count = 0;
            for (int j = 0; j < points.length; j++) {
                int distance = (points[i][0] - points[j][0]) * (points[i][0] - points[j][0]) + (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]);
                int tmp = map.getOrDefault(distance, 0);
                count += tmp * 2;
                map.put(distance, tmp+1);
            }
            res += count;
            map.clear();
        }
        return res;
    }
}
