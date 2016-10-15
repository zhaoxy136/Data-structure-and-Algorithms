/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 //Version 0: BFS, Two Stacks
 public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if(root == null){
            return results;
        }
        
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        boolean normalOrder = true;
        
        stack1.push(root);
        while(!stack1.isEmpty()){
            ArrayList<Integer> level = new ArrayList<>();
            //handle the nodes in stack1
            while(!stack1.isEmpty()){
                TreeNode tmp = stack1.pop();
                level.add(tmp.val);
                if(normalOrder){
                    if(tmp.left != null){
                        stack2.push(tmp.left);
                    }
                    if(tmp.right != null){
                        stack2.push(tmp.right);
                    }
                } else {
                    if(tmp.right != null){
                        stack2.push(tmp.right);
                    }
                    if(tmp.left != null){
                        stack2.push(tmp.left);
                    }
                }
            }
            
            results.add(level);
            //swap stack1 and stack2
            Stack<TreeNode> temp = stack1;
            stack1 = stack2;
            stack2 = temp;
            normalOrder = !normalOrder;
        }
        return results;

    }
}

//Version 1: One Queue with a reverse operation on odd rows of level before return.
public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if(root == null){
            return results;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            ArrayList<Integer> level = new ArrayList<>();
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode tmp = queue.poll();
                level.add(tmp.val);
                if(tmp.left != null){
                    queue.offer(tmp.left);
                }
                if(tmp.right != null){
                    queue.offer(tmp.right);
                }
            }
            results.add(level);
        }
        for(int i = 1; i < results.size(); i += 2){
            Collections.reverse(results.get(i));
        }
        return results;
    }
}
