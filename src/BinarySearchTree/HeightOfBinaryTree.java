package BinarySearchTree;

/**
* Given the root of a binary tree, return its depth.
* The depth of a binary tree is defined as the number of nodes
* along the longest path from the root node down to the farthest leaf node.
* */

public class HeightOfBinaryTree {
    /**
     * In simple word we have to find the total level of the binary tree
     * Assuming we have function which gives us the left subtree height and right subtree height.
     * Once we get botht the heights we will find the max between them and add one to add the root level as well.
     * */

    public int height (TreeNode root) {
        if (root == null) return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

}
