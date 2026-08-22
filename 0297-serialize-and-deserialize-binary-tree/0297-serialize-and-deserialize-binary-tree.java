public class Codec {

    // Serialize
    public String serialize(TreeNode root) {

        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        preorder(root, sb);

        return sb.toString();
    }

    private void preorder(TreeNode root, StringBuilder sb) {

        if (root == null) {
            sb.append("null,");
            return;
        }

        sb.append(root.val).append(",");

        preorder(root.left, sb);
        preorder(root.right, sb);
    }


    // Deserialize
    public TreeNode deserialize(String data) {

        if (data == null || data.isEmpty()) {
            return null;
        }

        String[] values = data.split(",");

        int[] index = {0};

        return buildTree(values, index);
    }


    private TreeNode buildTree(
            String[] values,
            int[] index) {

        if (index[0] >= values.length) {
            return null;
        }

        String value = values[index[0]];
        index[0]++;

        if (value.equals("null")) {
            return null;
        }

        TreeNode node = new TreeNode(
                Integer.parseInt(value)
        );

        node.left = buildTree(values, index);

        node.right = buildTree(values, index);

        return node;
    }
}