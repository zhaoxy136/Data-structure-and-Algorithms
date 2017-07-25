//Version 0: BFS
public class Solution {
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return -1;
        int[][] dist = new int[grid.length][grid[0].length];
        int[][] reach = new int[grid.length][grid[0].length];
        int buildings = 0;
        int[] dir = new int[]{1,0,-1,0,1};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    buildings++;
                    Queue<int[]> queue = new LinkedList<>();
                    boolean[][] visited = new boolean[grid.length][grid[0].length];
                    queue.offer(new int[]{i,j});
                    int level = 1;
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        while (size-- > 0) {
                            int[] index = queue.poll();
                            for (int k = 0; k < 4; k++) {
                                int row = index[0] + dir[k];
                                int col = index[1] + dir[k+1];
                                if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && !visited[row][col] && grid[row][col] == 0) {
                                    dist[row][col] += level;
                                    reach[row][col]++;
                                    visited[row][col] = true;
                                    queue.offer(new int[]{row,col});
                                }
                            }
                        }
                        level++;
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (reach[i][j] == buildings) {
                    res = Math.min(res, dist[i][j]);
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}

//Version 1:
//@copyright: https://discuss.leetcode.com/topic/31702/36-ms-c-solution
public class Solution {
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        int[] dir = new int[]{1, 0, -1, 0, 1};
        int target = 0;
        int minDist = -1;
        int[][] dist = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i,j});
                    int level = 1;
                    minDist = -1;
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        while (size-- > 0) {
                            int[] index = queue.poll();
                            for (int k = 0; k < 4; k++) {
                                int row = index[0] + dir[k];
                                int col = index[1] + dir[k+1];
                                if (row >= 0 && row < grid.length && col >= 0 
                                    && col < grid[0].length && grid[row][col] == target) {
                                    grid[row][col]--;
                                    queue.offer(new int[]{row,col});
                                    dist[row][col] += level;
                                    if (minDist == -1 || minDist > dist[row][col]) {
                                        minDist = dist[row][col];
                                    }
                                }
                            }
                        }
                        level++;
                    }
                    target--;
                }
            }
        }
        return minDist;
    }
}

