class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int area = helper(grid, i, j);
                    max = Math.max(max, area);
                }
            }
        }
        return max;
    }
    private int[] dir = {0, 1, 0, -1, 0};
    private int helper(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        int area = 1;
        for (int k = 0; k < 4; k++) {
            int row = i + dir[k];
            int col = j + dir[k+1];
            area += helper(grid, row, col);
        }
        return area;
    }
}
