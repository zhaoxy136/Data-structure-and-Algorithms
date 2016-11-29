//Version 0: BFS
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        for (int[] pair : prerequisites) {
            indegree[pair[0]]++;
        }
        Queue<Integer> queue = new LinkedList();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int canChoose = queue.size();
        while (!queue.isEmpty()) {
            int course = queue.poll();
            for (int[] pair : prerequisites) {
                if (pair[1] == course && --indegree[pair[0]] == 0) {
                    queue.offer(pair[0]);
                    canChoose++;
                }
            }
        }
        return canChoose == numCourses;
    }
}

//Version 1: BFS more efficient
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> list = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            list.add(new ArrayList<>());
        }
        for (int row = 0; row < prerequisites.length; row++) {
            int pre = prerequisites[row][1];
            int course = prerequisites[row][0];
            list.get(pre).add(course);
            indegree[course]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                count++;
            }
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            List<Integer> release = list.get(course);
            for (int num : release) {
                if (--indegree[num] == 0) {
                    queue.offer(num);
                    count++;
                }
            }
        }
        return count == numCourses;
    }
}

//Version 2: DFS
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] visit = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (visit[i] == 0 && !dfs(prerequisites, i, visit)) {
                return false;
            } 
        }
        return true;
    }
    //determine if any cycle exists starting from given node
    private boolean dfs(int[][] prerequisites, int course, int[] visit) {
        if (visit[course] == 2) return true; //has already been visited
        if (visit[course] == 1) return false; //being visited
        visit[course] = 1; 
        for (int[] pair : prerequisites) {
            if (pair[1] == course) {
                if (!dfs(prerequisites, pair[0], visit)) return false;
            }
        }
        visit[course] = 2;
        return true;
    }
}

//Version 3: DFS, much more efficient @copyright MadDetective 
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[numCourses];
        for(int i=0; i<numCourses; i++){
            adj[i] = new ArrayList<Integer>();
        }
        for(int[] pre : prerequisites){
            adj[pre[1]].add(pre[0]);
        }
        int[] status = new int[numCourses]; // 0: unvisited, 1:visiting, 2: visited
        for(int i=0; i<numCourses; i++){
            if(status[i]==0){
                if(dfs(i, adj, status) == false) return false;
            }
        }
        return true;
    }
    
    private boolean dfs(int root, List<Integer>[] adj, int[] status){
        status[root] = 1;
        for(int neighbor : adj[root]){
            if(status[neighbor]==0){
                if(dfs(neighbor, adj, status)==false) return false;
            } else if(status[neighbor]==1) return false;
        }
        status[root] = 2;
        return true;
    }
}
