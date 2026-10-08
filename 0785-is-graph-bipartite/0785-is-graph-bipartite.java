class Solution {

    public boolean isBipartite(int[][] graph) {

        int n = graph.length;

        // -1 = not colored
        //  0 = blue
        //  1 = red
        int[] color = new int[n];

        for (int i = 0; i < n; i++) {
            color[i] = -1;
        }

        // Graph can have multiple components
        for (int i = 0; i < n; i++) {

            if (color[i] == -1) {

                // Start DFS
                color[i] = 0;

                if (!dfs(graph, i, color)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean dfs(
            int[][] graph,
            int current,
            int[] color) {

        for (int neighbor : graph[current]) {

            // Neighbor is not colored
            if (color[neighbor] == -1) {

                // Give opposite color
                color[neighbor] = 1 - color[current];

                if (!dfs(graph, neighbor, color)) {
                    return false;
                }
            }

            // Neighbor already has same color
            else if (color[neighbor] == color[current]) {

                return false;
            }
        }

        return true;
    }
}