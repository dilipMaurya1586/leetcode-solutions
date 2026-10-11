import java.util.*;

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Arrays.asList(0);
        }

        List<List<Integer>> graph = new ArrayList<>();
        int[] degree = new int[n];

        // Create an empty neighbor list for every node
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Build the undirected graph
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);
            graph.get(v).add(u);

            degree[u]++;
            degree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        // Add all initial leaves
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }

        int remaining = n;

        // Remove leaf layers until at most 2 nodes remain
        while (remaining > 2) {
            int size = queue.size();
            remaining -= size;

            for (int i = 0; i < size; i++) {
                int leaf = queue.poll();

                for (int neighbor : graph.get(leaf)) {
                    degree[neighbor]--;

                    if (degree[neighbor] == 1) {
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return new ArrayList<>(queue);
    }
}