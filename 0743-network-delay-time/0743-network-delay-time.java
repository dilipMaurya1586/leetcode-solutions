import java.util.*;

class Solution {

    public int networkDelayTime(int[][] times, int n, int k) {

        // Build graph
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] time : times) {

            int from = time[0];
            int to = time[1];
            int weight = time[2];

            graph.get(from).add(new int[]{to, weight});
        }

        // Shortest distance from k
        int[] distance = new int[n + 1];

        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[k] = 0;

        // {node, distance}
        PriorityQueue<int[]> pq =
            new PriorityQueue<>((a, b) -> a[1] - b[1]);

        pq.offer(new int[]{k, 0});

        while (!pq.isEmpty()) {

            int[] current = pq.poll();

            int node = current[0];
            int currentDistance = current[1];

            // Ignore outdated entry
            if (currentDistance > distance[node]) {
                continue;
            }

            for (int[] edge : graph.get(node)) {

                int nextNode = edge[0];
                int weight = edge[1];

                int newDistance =
                    currentDistance + weight;

                if (newDistance < distance[nextNode]) {

                    distance[nextNode] = newDistance;

                    pq.offer(
                        new int[]{nextNode, newDistance}
                    );
                }
            }
        }

        // Find maximum shortest distance
        int answer = 0;

        for (int node = 1; node <= n; node++) {

            if (distance[node] == Integer.MAX_VALUE) {
                return -1;
            }

            answer = Math.max(answer, distance[node]);
        }

        return answer;
    }
}