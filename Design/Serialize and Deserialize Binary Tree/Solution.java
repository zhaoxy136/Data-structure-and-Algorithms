//Version 0: Level_Order
//may cause MLE,for extremlt unbalanced tree
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "null";
        int h = getHeight(root);
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size = 1;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < size; j++) {
                TreeNode tmp = queue.poll();
                if (tmp == null) {
                    sb.append("null");
                    queue.add(null);
                    queue.add(null);
                } else {
                    sb.append(tmp.val);
                    queue.add(tmp.left);
                    queue.add(tmp.right);
                }
                sb.append(",");
            }
            size *= 2;
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    
    public int getHeight(TreeNode root) {
        if (root == null) return 0;
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return Math.max(left, right) + 1;
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.trim().split(",");
        return helper(nodes, 0);
    }
    public TreeNode helper(String[] nodes, int index) {
        if (index >= nodes.length) return null;
        if (nodes[index].equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(nodes[index]));
        root.left = helper(nodes, index * 2 + 1);
        root.right = helper(nodes, index * 2 + 2);
        return root;
    }
}


//Version 1: PreOrder
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        helper(root, sb);
        return sb.toString();
    }
    public void helper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append('#').append(',');
        } else {
            sb.append(root.val).append(',');
            helper(root.left, sb);
            helper(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>();
        String[] nodes = data.split(",");
        for (String node : nodes) {
            queue.offer(node);
        }
        return buildTree(queue);
    }
    
    public TreeNode buildTree(Queue<String> queue) {
        String node = queue.poll();
        if (node.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.valueOf(node));
        root.left = buildTree(queue);
        root.right = buildTree(queue);
        return root;
    }
}
