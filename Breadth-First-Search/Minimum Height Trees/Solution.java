//Version 0:
public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n <= 1) return new ArrayList<>(Arrays.asList(0));
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (list.get(i).size() == 1) res.add(i);
        }
        while (n > 2) {
            n -= res.size();
            List<Integer> leaves = new ArrayList<>();
            for (int l : res) {
                int tmp = list.get(l).get(0);
                list.get(tmp).remove((Object)l);
                if (list.get(tmp).size() == 1) leaves.add(tmp);
            }
            res = leaves;
        }
        return res;
    }
    
//Version 1:
public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n <= 1) return new ArrayList<>(Arrays.asList(0));
        List<List<Integer>> adj = new ArrayList<>(n);
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
            indegree[edge[0]]++;
            indegree[edge[1]]++;
        }
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 1) {
                leaves.add(i);
            }
        }
        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for (int leaf : leaves) {
                for (int tmp : adj.get(leaf)) {
                    if (--indegree[tmp] == 1) newLeaves.add(tmp);
                }
            }
            leaves = newLeaves;
        }
        return leaves;
    }
