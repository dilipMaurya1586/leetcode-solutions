import java.util.*;

class Solution {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        int[] cost = new int[n];

        Arrays.fill(cost, Integer.MAX_VALUE);

        cost[src] = 0;

        // Maximum k stops = k + 1 flights
        for (int stops = 0; stops <= k; stops++) {

            int[] nextCost = cost.clone();

            for (int[] flight : flights) {

                int from = flight[0];
                int to = flight[1];
                int price = flight[2];

                if (cost[from] == Integer.MAX_VALUE) {
                    continue;
                }

                nextCost[to] = Math.min(
                        nextCost[to],
                        cost[from] + price);
            }

            cost = nextCost;
        }

        return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
    }
}