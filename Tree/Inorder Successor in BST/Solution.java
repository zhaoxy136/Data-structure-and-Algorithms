//Version 0:

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p == null) return null;
        if (p.right != null) {
            TreeNode cur = p.right;
            while (cur.left != null) cur = cur.left;
            return cur;
        }
        TreeNode cur = root;
        TreeNode prev = null;
        while (cur != p) {
            if (p.val < cur.val) {
                prev = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return prev;
    }
    
//Version 1:

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p == null) return null;
        TreeNode cur = root;
        TreeNode prev = null;
        while (cur != null) {
            if (p.val < cur.val) {
                prev = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return prev;
    }
