public class Solution {
    public class Cell {
        int r;
        int c;
        int h;
        public Cell(int row, int col, int height) {
            this.r = row;
            this.c = col;
            this.h = height;
        }
    }
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0) return 0;
        int m = heightMap.length;
        int n = heightMap[0].length;
        PriorityQueue<Cell> queue = new PriorityQueue<>(new Comparator<Cell>() {
            public int compare(Cell a, Cell b) {
                return a.h - b.h;
            }
        });
        for (int i = 0; i < m; i++) {
            queue.add(new Cell(i, 0, heightMap[i][0]));
            queue.add(new Cell(i, n-1, heightMap[i][n-1]));
            heightMap[i][0] = -1;
            heightMap[i][n-1] = -1;
        }
        for (int i = 1; i < n-1; i++) {
            queue.add(new Cell(0, i, heightMap[0][i]));
            queue.add(new Cell(m-1, i, heightMap[m-1][i]));
            heightMap[0][i] = -1;
            heightMap[m-1][i] = -1;
        }
        int res = 0;
        int[] dir = new int[]{0, 1, 0, -1, 0};
        while (!queue.isEmpty()) {
            Cell cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int row = cur.r + dir[i];
                int col = cur.c + dir[i+1];
                if (row >= 0 && col >= 0 && row < m && col < n && heightMap[row][col] != -1) {
                    res += Math.max(0, cur.h - heightMap[row][col]);
                    queue.add(new Cell(row, col, Math.max(cur.h, heightMap[row][col])));//NOTE:GET THE MAX AS NEW HEIGHT!
                    heightMap[row][col] = -1;
                }
            }
        }
        return res;
    }
}
