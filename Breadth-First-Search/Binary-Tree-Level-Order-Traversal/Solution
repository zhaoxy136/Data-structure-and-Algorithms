/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 //Version 0: (BFS,One Queue)
 public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            TreeNode tmp;
            ArrayList<Integer> level = new ArrayList<>();
            for(int i = 0; i < size; i++){
                tmp = queue.poll();
                level.add(tmp.val);
                if(tmp.left != null){
                    queue.offer(tmp.left);
                }
                if(tmp.right != null){
                    queue.offer(tmp.right);
                }
            }
            result.add(level);
        }
        return result;
    }
}

//Version 1:(BFS, Two Queues)
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        
        queue1.offer(root);
        while(!queue1.isEmpty()){
            ArrayList<Integer> level = new ArrayList<>();
            while(!queue1.isEmpty()){
                TreeNode tmp = queue1.poll();
                level.add(tmp.val);
                if(tmp.left != null){
                    queue2.offer(tmp.left);
                }
                if(tmp.right != null){
                    queue2.offer(tmp.right);
                }
            }
            result.add(level);
            Queue<TreeNode> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }
        return result;
    }
}

//Version 2: (BFS,One Queue with Dummy Node)
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> level = new ArrayList<>();
        
        queue.offer(root);
        queue.offer(null);
        while(!queue.isEmpty()){
            TreeNode tmp = queue.poll();
            if(tmp == null){
                if(level.size() == 0){
                    break;
                }
                result.add(level);
                level = new ArrayList<>();
                queue.offer(null);
            } else{
                level.add(tmp.val);
                if(tmp.left != null){
                    queue.offer(tmp.left);
                }
                if(tmp.right != null){
                    queue.offer(tmp.right);
                }
              }
        }
        return result;
    }
}
