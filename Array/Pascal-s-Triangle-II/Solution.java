public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        int[] f = new int[rowIndex+1];
        f[0] = 1;
        for (int i = 1; i <= rowIndex; i++){
            f[i] = 1;
            for (int j = i-1; j > 0; j--){
                f[j] = f[j-1] + f[j];
            }
        }
        for (int i = 0; i <= rowIndex; i++){
            res.add(f[i]);
        }
        return res;
    }
}
