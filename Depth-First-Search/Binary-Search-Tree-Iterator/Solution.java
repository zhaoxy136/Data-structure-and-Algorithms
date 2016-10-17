/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
    Stack<TreeNode> stack = new Stack<>();
    private TreeNode curtNode;
    public BSTIterator(TreeNode root) {
        this.curtNode = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return (curtNode != null || !stack.isEmpty());
    }

    /** @return the next smallest number */
    public int next() {
        while(curtNode != null){
            stack.push(curtNode);
            curtNode = curtNode.left;
        }
        TreeNode node = stack.pop();
        curtNode = node.right;
        return node.val;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
