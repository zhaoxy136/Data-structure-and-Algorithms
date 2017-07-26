//Version 0:
public class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        int[] dir = new int[]{0,1,0,-1,0};
        int[] roots = new int[m*n];
        Arrays.fill(roots, -1);
        int count = 0;
        for (int[] pos : positions) {
            int root = pos[0]*n+pos[1];
            roots[root] = root;
            count++;
            for (int i = 0; i < 4; i++) {
                int row = pos[0] + dir[i];
                int col = pos[1] + dir[i+1];
                int val = row * n + col;
                if (row >= 0 && row < m && col >= 0 && col < n && roots[val] != -1) {
                    while (val != roots[val]) val = roots[val];
                    if (val != roots[root]) {
                        roots[root] = val;
                        root = val;
                        count--;
                    }
                    
                }
            }
            res.add(count);
        }
        return res;
    }
}
