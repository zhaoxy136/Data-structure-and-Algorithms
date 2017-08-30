//Version 0: DFS
class Solution {
    public int countComponents(int n, int[][] edges) {
        if (n < 1) return 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            //have to add both edge to the map
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        int count = 0;
        int[] visited = new int[n];
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                count++;
                helper(map, visited, i);
            }
        }
        return count;
    }
    
    public void helper(Map<Integer, List<Integer>> map, int[] visited, int key) {
        visited[key] = 1;
        for (int tmp : map.get(key)) {
            if (visited[tmp] == 0) {
                helper(map, visited, tmp);
            }
        }
    }
    
}

//Version 1: Union-Find
class Solution {
    class UF {
        public int count;
        public int[] id;
        public int[] sz;
        public UF(int n) {
            this.count = n;
            this.id = new int[n];
            this.sz = new int[n];
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
    public int countComponents(int n, int[][] edges) {
        UF uf = new UF(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }
        return uf.count;
    }
}
