//Version 0:
public class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) return;
        int n = rooms[0].length;
        Queue<Integer> queue = new LinkedList<>();
        int[][] directions = new int[][]{
            {-1,0}, {1,0}, {0,-1}, {0,1}
        };
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(i*n+j);
                }
            }
        }
        while (!queue.isEmpty()) {
            int row = queue.peek() / n;
            int col = queue.poll() % n;
            for (int[] dir : directions) {
                int x = row + dir[0];
                int y = col + dir[1];
                if (x >= 0 && y >= 0 && x < rooms.length && y < n && rooms[x][y] == Integer.MAX_VALUE) {
                    rooms[x][y] = rooms[row][col] + 1;
                    queue.offer(x * n + y);
                }
            }
        }
        
    }
}
