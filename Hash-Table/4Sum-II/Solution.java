public class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int n = A.length;
        if (n == 0) return 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = map.getOrDefault(-A[i]-B[j], 0);
                map.put(-A[i]-B[j], tmp+1);
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = map.getOrDefault(C[i]+D[j], 0);
                count += tmp;
            }
        }
        return count;
    }
}
