class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            // Go as far left as possible, pushing nodes onto the stack
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // Visit the node
            curr = stack.pop();
            k--;
            if (k == 0) {
                return curr.val;
            }

            // Move to the right subtree
            curr = curr.right;
        }

        // k is guaranteed valid per constraints, so this line is unreachable
        throw new IllegalStateException("k is out of bounds for the given tree");
    }
}