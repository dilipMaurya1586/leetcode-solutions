class Solution {
    public int swimInWater(int[][] grid) {

        int n = grid.length;

        int[][] distance = new int[n][n];

        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        distance[0][0] = grid[0][0];
        pq.offer(new int[] { 0, 0, grid[0][0] });

        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        while (!pq.isEmpty()) {
            int[] current = pq.poll();

            int row = current[0];
            int col = current[1];
            int currentTime = current[2];

            if (currentTime > distance[row][col]) {
                continue;
            }

            if (row == n - 1 && col == n - 1) {
                return currentTime;
            }

            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];
                if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= n) {
                    continue;
                }
                int newTime = Math.max(currentTime, grid[newRow][newCol]);

                if (newTime < distance[newRow][newCol]) {
                    distance[newRow][newCol] = newTime;
                    pq.offer(new int[] { newRow, newCol, newTime });
                }
            }
        }
        return -1;
    }

}