//Version 0: original, BFS
public class Solution {
    private class Indices {
        private int row;
        private int col;
        private Indices(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    visited[i][j] = true;
                    helper(grid, visited, new Indices(i, j));
                    count++;
                }
            }
        }
        return count;
    }
    private void helper(char[][] grid, boolean[][] visited, Indices indices) {
        Queue<Indices> queue = new LinkedList<>();
        queue.offer(indices);
        while (!queue.isEmpty()) {
            Indices tmp = queue.poll();
            int row = tmp.row;
            int col = tmp.col;
            if (col < grid[0].length - 1 && grid[row][col+1] == '1' && !visited[row][col+1]) {
                queue.offer(new Indices(row, col+1));
                visited[row][col+1] = true;
            }
            if (col > 0 && grid[row][col-1] == '1' && !visited[row][col-1]) {
                queue.offer(new Indices(row, col-1));
                visited[row][col-1] = true;
            }
            if (row < grid.length - 1 && grid[row+1][col] == '1' && !visited[row+1][col]) {
                queue.offer(new Indices(row+1, col));
                visited[row+1][col] = true;
            }
            if (row > 0 && grid[row-1][col] == '1' && !visited[row-1][col]) {
                queue.offer(new Indices(row-1, col));
                visited[row-1][col] = true;
            }
        }
    }
}

//Version 1: much more easy, DFS
public class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    visitIsland(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    private void visitIsland(char[][] grid, int row, int col) {
        if (row < 0 || row == grid.length || col < 0 || col == grid[0].length || grid[row][col] == '0') {
            return;
        }
        grid[row][col] = '0';
        visitIsland(grid, row+1, col);
        visitIsland(grid, row-1, col);
        visitIsland(grid, row, col+1);
        visitIsland(grid, row, col-1);
    }
}
