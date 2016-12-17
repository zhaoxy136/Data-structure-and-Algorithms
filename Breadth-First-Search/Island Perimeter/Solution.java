//Version 0: 
public class Solution {
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
                return 0;
        }
        int count = 0;
        int inEdge = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    count++;
                    inEdge += countEdge(grid, i, j);
                }
            }
        }
        return (count * 4 - inEdge);
    }
    private int countEdge(int[][] grid, int i, int j) {
        int edge = 0;
        if (i > 0) {
            edge += grid[i-1][j];
        }
        if (i < grid.length-1) {
            edge += grid[i+1][j];
        }
        if (j > 0) {
            edge += grid[i][j-1];
        }
        if (j < grid[0].length-1) {
            edge += grid[i][j+1];
        }
        return edge;
    }
}

//Version 1: great solution
//@copyright https://discuss.leetcode.com/topic/68786/clear-and-easy-java-solution
public class Solution {
    public int islandPerimeter(int[][] grid) {
        int islands = 0, neighbours = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    islands++; // count islands
                    if (i < grid.length - 1 && grid[i + 1][j] == 1) neighbours++; // count down neighbours
                    if (j < grid[i].length - 1 && grid[i][j + 1] == 1) neighbours++; // count right neighbours
                }
            }
        }

        return islands * 4 - neighbours * 2;
    }
}
