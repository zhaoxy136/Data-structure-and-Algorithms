//LeetCode: https://leetcode.com/problems/binary-tree-vertical-order-traversal/description/


//Version 0:

class Solution {
    class Node {
        TreeNode node;
        int pos;
        Node(TreeNode node, int pos) {
            this.node = node;
            this.pos = pos;
        }
    }
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Node> queue = new LinkedList<>();
        
        queue.add(new Node(root, 0));
        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            int position = tmp.pos;
            if (!map.containsKey(position)) map.put(position, new ArrayList<>());
            map.get(position).add(tmp.node.val);
            if (tmp.node.left != null) queue.add(new Node(tmp.node.left, position-1));
            if (tmp.node.right != null) queue.add(new Node(tmp.node.right, position + 1));
        }
        
        for (int key : map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }
}

//Version 1: using two queues instead of customized class
public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> position = new LinkedList<>();
        queue.add(root);
        position.add(0);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int pos = position.poll();
            if (!map.containsKey(pos)) map.put(pos, new ArrayList<>());
            map.get(pos).add(node.val);
            if (node.left != null) {
                queue.add(node.left);
                position.add(pos-1);
            }
            if (node.right != null) {
                queue.add(node.right);
                position.add(pos+1);
            }
        }
        
        for (int key : map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }
    
//Version 2: record the boundary instead of using treemap
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        int min = 0;
        int max = 0;
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> position = new LinkedList<>();
        queue.add(root);
        position.add(0);  
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int pos = position.poll();
            min = Math.min(min, pos);
            max = Math.max(max, pos);
            if (!map.containsKey(pos)) map.put(pos, new ArrayList<>());
            map.get(pos).add(node.val);
            if (node.left != null) {
                queue.add(node.left);
                position.add(pos-1);
            }
            if (node.right != null) {
                queue.add(node.right);
                position.add(pos+1);
            }
        }
        
        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }
        return res;
    }
}




