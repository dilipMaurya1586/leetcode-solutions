class Solution {

    public int orangesRotting(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();

        int fresh = 0;

        // Find rotten and fresh oranges
        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < cols; col++) {

                if (grid[row][col] == 2) {

                    queue.offer(new int[]{row, col});

                } else if (grid[row][col] == 1) {

                    fresh++;
                }
            }
        }

        int minutes = 0;

        // BFS
        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int[] current = queue.poll();

                int row = current[0];
                int col = current[1];

                // Four directions
                int[][] directions = {
                    {-1, 0},
                    {1, 0},
                    {0, -1},
                    {0, 1}
                };

                for (int[] dir : directions) {

                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    // Boundary check
                    if (newRow < 0 || newRow >= rows ||
                        newCol < 0 || newCol >= cols) {
                        continue;
                    }

                    // Fresh orange?
                    if (grid[newRow][newCol] == 1) {

                        grid[newRow][newCol] = 2;

                        fresh--;

                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }

            if (!queue.isEmpty()) {
                minutes++;
            }
        }

        // Fresh oranges still remaining
        if (fresh > 0) {
            return -1;
        }

        return minutes;
    }
}