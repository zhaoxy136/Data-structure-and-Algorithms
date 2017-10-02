//peeling onions
//recursion
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return res;
        helper(res, matrix, 0, matrix.length - 1, 0, matrix[0].length - 1);
        return res;
    }
    public void helper(List<Integer> res, int[][] matrix, int r1, int r2, int c1, int c2) {
        if (r1 > r2 || c1 > c2) return;
        for (int i = c1; i <= c2; i++) {
            res.add(matrix[r1][i]);
        }
        for (int i = r1 + 1; i <= r2; i++) {
            res.add(matrix[i][c2]);
        }
        if (r1 < r2 && c1 < c2) {
            for (int i = c2 - 1; i >= c1; i--) {
                res.add(matrix[r2][i]);
            }
            for (int i = r2 - 1; i > r1; i--) {
                res.add(matrix[i][c1]);
            }
        }
        helper(res, matrix, ++r1, --r2, ++c1, --c2);
    }
}
