public class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for (List<Integer> list : seqs) {
            for (int i = 0; i < list.size(); i++) {
                map.putIfAbsent(list.get(i), new HashSet<Integer>());
                indegree.putIfAbsent(list.get(i), 0);
                if (i > 0) {
                    if (map.get(list.get(i-1)).add(list.get(i))) { 
                        indegree.put(list.get(i), indegree.get(list.get(i)) + 1);
                    }
                }
            }
        }
        if (org.length != indegree.size()) return false;
        Queue<Integer> queue = new LinkedList<>();
        for (Integer key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                queue.offer(key);
            }
        }
        int index = 0;
        while (!queue.isEmpty()) {
            if (queue.size() > 1) return false;
            int cur = queue.poll();
            if (org[index++] != cur) return false;
            for (int next : map.get(cur)) {
                indegree.put(next, indegree.get(next)-1);
                if (indegree.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }
        return index == org.length;
    }
}
