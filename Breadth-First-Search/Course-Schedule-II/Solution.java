//Version 0: BFS
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjs = new ArrayList<>();
        int[] list = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adjs.add(new ArrayList<>());
        }
        int count = 0;
        int[] indegree = new int[numCourses];
        for (int[] pair : prerequisites) {
            indegree[pair[0]]++;
            adjs.get(pair[1]).add(pair[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            list[count++] = course;
            for (int next : adjs.get(course)) {
                if (--indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        return count == numCourses ? list : new int[0];
    }
}

//Version 1: DFS
