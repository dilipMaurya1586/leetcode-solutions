/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // Global array index tracking pointers
    private int preorderIndex = 0;
    private int inorderIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        inorderIndex = 0;
        // Start building with an initial upper boundary limit
        return build(preorder, inorder, Integer.MAX_VALUE);
    }

    private TreeNode build(int[] preorder, int[] inorder, int stopValue) {
        // Base Case 1: Out of elements to process
        if (preorderIndex >= preorder.length) {
            return null;
        }

        // Base Case 2: The current inorder element matches our ancestor boundary.
        // This signifies the current left or right subtree path has ended.
        if (inorder[inorderIndex] == stopValue) {
            inorderIndex++; // Move past this consumed root element
            return null;
        }

        // Step 1: Initialize the local root node using the preorder stream
        TreeNode root = new TreeNode(preorder[preorderIndex++]);

        // Step 2: Build the left subtree.
        // The current node's value becomes the new strict upper boundary limit.
        root.left = build(preorder, inorder, root.val);

        // Step 3: Build the right subtree.
        // The right side inherits the existing parent boundary constraints.
        root.right = build(preorder, inorder, stopValue);

        return root;
    }
}
