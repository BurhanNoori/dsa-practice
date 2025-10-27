package BinarySearchTree;

/**
 * Assume we have a function which solves our problem
 * Now give it left subtree and it does the magic (invert left subtree)
 * Now give it right subtree and it does the magic (invert right subtree)
 * Once the problem is solved you do final step means swaping the left subtree with right subtee
 * */

public class InvertTree {

    public TreeNode invert (TreeNode root) {
        if (root == null) return root;

        TreeNode temp = root.left;
        root.left = invert(root.right);
        root.right = invert(temp);

        return root;
    }

}
