//Version 0: original, just like copy graph
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        Map<TreeNode, TreeNode> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        map.put(root, new TreeNode(root.val));
        while (!queue.isEmpty()) {
            TreeNode tmp = queue.poll();
            if (tmp.left != null) {
                queue.offer(tmp.left);
                map.put(tmp.left, new TreeNode(tmp.left.val));
                map.get(tmp).right = map.get(tmp.left);
            }
            if (tmp.right != null) {
                queue.offer(tmp.right);
                map.put(tmp.right, new TreeNode(tmp.right.val));
                map.get(tmp).left = map.get(tmp.right);
            }
        }
        return map.get(root);
    }
}

//Version 1: why use hashmap !
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}

//Version 2: same as iterative
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) continue;
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return root;
    }
}
