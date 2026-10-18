class Solution {
    int[] parent;
    int[] rank;

    public int removeStones(int[][] stones) {
        int n = stones.length;

        parent = new int[n];
        rank = new int[n];

        // Initially, every stone is its own group
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // Join stones that share a row or column
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (stones[i][0] == stones[j][0] ||
                    stones[i][1] == stones[j][1]) {
                    union(i, j);
                }
            }
        }

        // Count connected groups
        int components = 0;

        for (int i = 0; i < n; i++) {
            if (find(i) == i) {
                components++;
            }
        }

        return n - components;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) {
            return;
        }

        if (rank[rootA] < rank[rootB]) {
            parent[rootA] = rootB;
        } else if (rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA;
        } else {
            parent[rootB] = rootA;
            rank[rootA]++;
        }
    }
}