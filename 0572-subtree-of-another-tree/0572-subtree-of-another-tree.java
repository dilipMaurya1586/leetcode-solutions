class Solution {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null) {
            return false;
        }
        if(subRoot == null) {
            return true;
        }
        if(isSametree(root, subRoot)) {
            return true;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean isSametree(TreeNode p, TreeNode q) {
        if(p == null && q == null) {
            return true;
        }
        if(p==null || q==null || p.val != q.val) {
            return false;
        }
        return isSametree(p.left, q.left) && isSametree(p.right, q.right);
    }

}