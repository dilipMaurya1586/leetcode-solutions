class Solution {

    public int minCostConnectPoints(int[][] points) {

        int n = points.length;

        boolean[] visited = new boolean[n];

        int[] minCost = new int[n];

        // Initially, every point has infinite cost
        for (int i = 0; i < n; i++) {
            minCost[i] = Integer.MAX_VALUE;
        }

        // Start from point 0
        minCost[0] = 0;

        int totalCost = 0;

        for (int count = 0; count < n; count++) {

            // Find unvisited point with minimum cost
            int current = -1;

            for (int i = 0; i < n; i++) {

                if (!visited[i] &&
                    (current == -1 ||
                     minCost[i] < minCost[current])) {

                    current = i;
                }
            }

            // Add this point to MST
            visited[current] = true;
            totalCost += minCost[current];

            // Update cost of remaining points
            for (int next = 0; next < n; next++) {

                if (!visited[next]) {

                    int distance =
                        Math.abs(points[current][0] - points[next][0])
                        +
                        Math.abs(points[current][1] - points[next][1]);

                    minCost[next] =
                        Math.min(minCost[next], distance);
                }
            }
        }

        return totalCost;
    }
}