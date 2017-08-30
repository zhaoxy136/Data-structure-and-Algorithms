class Solution {
    class UF {
        int count;
        int[] id;
        int[] sz;
        public UF(int n) {
            count = n;
            id = new int[n];
            sz = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
                sz[i] = 1;
            }
        }
        
        public int find(int p) {
            while (p != id[p]) {
                id[p] = id[id[p]];
                p = id[p];
            }
            return p;
        }
        
        public boolean union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return false;
            if (sz[pRoot] > sz[qRoot]) {
                id[qRoot] = pRoot;
                sz[pRoot] += sz[qRoot];
            } else {
                id[pRoot] = qRoot;
                sz[qRoot] += sz[pRoot];
            }
            count--;
            return true;
        }
    }
    public boolean validTree(int n, int[][] edges) {
        if (n <= 0) return false;
        UF uf = new UF(n);
        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) return false;
        }
        return uf.count == 1;
    }
}
