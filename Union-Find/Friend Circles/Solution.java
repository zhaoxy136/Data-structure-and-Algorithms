//Version 0: DFS
class Solution {
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) return 0;
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (M[i][i] == 1) {
                count++;
                M[i][i] = 0;
                helper(M, i);
            }
        }
        return count;
    }
    
    public void helper(int[][] M, int row) {
        
        for (int j = 0; j < M[row].length; j++) {
            if (M[row][j] == 1) {
                M[row][j] = 0;
                M[j][row] = 0;
                helper(M, j);
            }
        }
    }
}

//Version 1: Union-Find
class Solution {
    class UF {
        private int count;
        private int[] id;
        private int[] sz;
        public UF(int n) {
            count = n;
            id = new int[n];
            sz = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
                sz[i] = 1;
            }
        }
        public int getCount() {
            return count;
        }
        
        public int find(int p) {
            while (p != id[p]) {
                id[p] = id[id[p]];
                p = id[p];
            }
            return p;
        }
    
        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            if (sz[pRoot] > sz[qRoot]) {
                id[qRoot] = pRoot;
                sz[pRoot] += sz[qRoot];
            } else {
                id[pRoot] = qRoot;
                sz[qRoot] += sz[pRoot];
            }
            count--;
        }
    }
    
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) return 0;
        UF uf = new UF(M.length);
        for (int i = 0; i < M.length; i++) {
            for (int j = i + 1; j < M[0].length; j++) {
                if (M[i][j] == 1)
                    uf.union(i, j);
            }
        }
        return uf.getCount();
    }
}
