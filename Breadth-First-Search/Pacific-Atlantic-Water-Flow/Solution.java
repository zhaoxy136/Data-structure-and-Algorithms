//Version 0: DFS
// Very nice solutions @ copyright https://discuss.leetcode.com/topic/62379/java-bfs-dfs-from-ocean
public class Solution {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return res;
        }
        boolean[][] paci = new boolean[matrix.length][matrix[0].length];
        boolean[][] atla = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            dfs(matrix, paci, i, 0);
            dfs(matrix, atla, i, matrix[0].length-1);
        }
        for (int i = 0; i < matrix[0].length; i++) {
            dfs(matrix, paci, 0, i);
            dfs(matrix, atla, matrix.length-1, i);
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (paci[i][j] && atla[i][j]) {
                    res.add(new int[]{i, j});
                }
            }
        }
        return res;
    }
    
    private void dfs(int[][] matrix, boolean[][] visited, int row, int col) {
        if (row < 0 || row == matrix.length || col < 0 || col == matrix[0].length || visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        if (row > 0 && matrix[row-1][col] >= matrix[row][col]) {
            dfs(matrix, visited, row-1, col);
        }
        if (row < matrix.length-1 && matrix[row+1][col] >= matrix[row][col]) {
            dfs(matrix, visited, row+1, col);
        }
        if (col > 0 && matrix[row][col-1] >= matrix[row][col]) {
            dfs(matrix, visited, row, col-1);
        }
        if (col < matrix[0].length-1 && matrix[row][col+1] >= matrix[row][col]) {
            dfs(matrix, visited, row, col+1);
        }
    }
}
