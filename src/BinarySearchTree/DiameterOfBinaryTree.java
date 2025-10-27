package BinarySearchTree;

/**
 * The Diameter of the Binary Tree is the total longest path
 * between any two node, it may or may not pass through the root
 * */

public class DiameterOfBinaryTree {
    /**
     * To get the diameter of a binary tree can lies
     * Case 1: It is combining the left subtree and right subtree
     * Case 2: It is in the left subtree
     * Case 3: It is in the right subtree
     * During DFS we would start from left so we would get deeper into the left part. Now considering case 2
     * This is the part where we may get the diameter so we will take the total height of the smallest left subtree
     * and compare with diameter. In this way we get the max from here.
     * Similarly applying same logic at the right side of the subtree.
     * @param root
     * @return TreeNode
     */
    private int diameter;
    public int getDiameter(TreeNode root) {
        diameter = 0;
        if (root == null) return 0;
        height(root);
        return diameter;
    }

    public int height(TreeNode root) {

        if (root == null) return 0;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        diameter = Math.max(diameter, leftHeight + rightHeight + 1);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Assume we have a function which gives us the diameter.
     * So we use this function and gets the diameter of left and right subtree (Case 2 & 3).
     * Once we get the diameter from the left and right subtree we will take combine the height of left subtree
     * and right subtree (Case 1) and find the max from all
     *
     * */
    public int getDiameter2 (TreeNode root) {

        if (root == null) return 0;

        int leftDia = getDiameter2(root.left);
        int rightDia = getDiameter2(root.right);
        int dia = height2(root.left) + height(root.right); // + 1; Don't add one because diameter is the number of edges not the nodes

        return Math.max(dia, Math.max(leftDia, rightDia));
    }

    public int height2(TreeNode root) {
        if (root == null) return 0;
        int leftHeight =  height2(root.left);
        int rightHeight = height2(root.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

}
