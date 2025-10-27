package Atlassian;

public class LCA {
    /**
     * To solve these problem we have to consider 4 situations
     * Case1: Both p and q are the left side of subtree
     * Case2: Both p and q are the right side of subtree
     * Case3: p and q are the either side of the subtree
     * Case4: Either root is null or p or q is null
     *              1
     *          2       3
     *       null
     * */
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(TreeNode left, TreeNode right, int val) {
            this.left = left;
            this.right = right;
            this.val = val;
        }
    }

    public TreeNode lowestCommonAncestor (TreeNode root, TreeNode p, TreeNode q) {

        //Case 4: Either root or p or q is null
        if (root == null || p == null || q == null) return null;

        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);

        //Case 3
        if (leftNode != null && rightNode != null) return root;

        //Case 1 or Case 2
        return leftNode != null ? leftNode : rightNode;

    }

    /*One more way to solve the problem. Using while loops it is having O(h) time and O(1) space complexity*/
    //If it is a binary search tree means left is less than the parent and right is greater than the parent
    public TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;
        while (curr != null) {
            if(p.val > curr.val && q.val > curr.val) {
                curr = curr.right;
            } else if(p.val < curr.val && q.val < curr.val) {
                curr = curr.left;
            } else {
                return curr;
            }
        }
        return curr;
    }

}
