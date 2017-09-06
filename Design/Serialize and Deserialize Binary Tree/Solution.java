//Version 0:
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
