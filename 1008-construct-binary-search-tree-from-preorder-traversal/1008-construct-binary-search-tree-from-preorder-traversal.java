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

    int index = 0;

    public TreeNode bstFromPreorder(int[] preorder) {

        return buildTree(preorder, Long.MIN_VALUE, Long.MAX_VALUE);

    }

    private TreeNode buildTree(int[] preorder, long min, long max) {

        if (index >= preorder.length) {
            return null;
        }
        int value = preorder[index];

        if (value <= min || value >= max) {
            return null;
        }
        TreeNode root = new TreeNode(value);
        index++;
        root.left = buildTree(preorder, min, value);
        root.right = buildTree(preorder, value, max);
        return root;
    }

}