class Solution {

    int[] parent;

    public int[] findRedundantConnection(int[][] edges) {

        int n = edges.length;

        // Initially, every node is its own parent
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // Process every edge
        for (int[] edge : edges) {

            int a = edge[0];
            int b = edge[1];

            int rootA = find(a);
            int rootB = find(b);

            // Already connected → cycle
            if (rootA == rootB) {
                return edge;
            }

            // Connect both groups
            parent[rootA] = rootB;
        }

        return new int[0];
    }

    private int find(int node) {

        if (parent[node] == node) {
            return node;
        }

        return parent[node] = find(parent[node]);
    }
}