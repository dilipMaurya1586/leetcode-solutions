class Solution {

    int[] parent;

    public boolean[] friendRequests(
            int n,
            int[][] restrictions,
            int[][] requests) {

        parent = new int[n];

        // Initially every person is their own parent
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        boolean[] answer = new boolean[requests.length];

        for (int i = 0; i < requests.length; i++) {

            int u = requests[i][0];
            int v = requests[i][1];

            boolean allowed = true;

            int rootU = find(u);
            int rootV = find(v);

            // Check every restriction
            for (int[] restriction : restrictions) {

                int a = restriction[0];
                int b = restriction[1];

                int rootA = find(a);
                int rootB = find(b);

                // Restriction blocks this merge
                if ((rootA == rootU && rootB == rootV) ||
                    (rootA == rootV && rootB == rootU)) {

                    allowed = false;
                    break;
                }
            }

            if (allowed) {
                answer[i] = true;

                // Merge the two groups
                union(rootU, rootV);
            }
        }

        return answer;
    }

    private int find(int x) {

        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    private void union(int a, int b) {

        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }
}