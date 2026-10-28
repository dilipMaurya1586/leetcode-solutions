class Solution {
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        return check(root, root.val);
    }

    private boolean check(TreeNode node, int value) {
        if (node == null) {
            return true;
        }

        if (node.val != value) {
            return false;
        }

        return check(node.left, value)
            && check(node.right, value);
    }
}