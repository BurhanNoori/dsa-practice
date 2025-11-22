package BinarySearchTree;

public class LowestCommonAncestor {

    /**
     * To solve these problem we have to consider 4 situations
     * Case1: Both p and q are the left side of subtree
     * Case2: Both p and q are the right side of subtree
     * Case3: p and q are the either side of the subtree
     * Case4: Either of them equals to root
     * */

    public TreeNode lca (TreeNode root, TreeNode p, TreeNode q) {
        //Case 4
        if (root == null || p.val == root.val || q.val == root.val) return root;
        TreeNode leftSide = lca(root.left, p, q);
        TreeNode rightSide = lca(root.right, p, q);
        if (leftSide != null && rightSide != null) return root;
        return leftSide != null ? leftSide:rightSide;

    }
}
