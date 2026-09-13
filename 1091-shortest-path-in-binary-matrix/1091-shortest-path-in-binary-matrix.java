import java.util.*;

class Solution {

    public int shortestPathBinaryMatrix(int[][] grid) {

        int n = grid.length;

        // Start or destination is blocked
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }

        Queue<int[]> queue = new LinkedList<>();

        // Start from top-left
        queue.offer(new int[]{0, 0});

        // Mark visited
        grid[0][0] = 1;

        int pathLength = 1;

        int[][] directions = {
            {-1, -1}, // top-left
            {-1, 0},  // up
            {-1, 1},  // top-right
            {0, -1},  // left
            {0, 1},   // right
            {1, -1},  // bottom-left
            {1, 0},   // down
            {1, 1}    // bottom-right
        };

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int[] current = queue.poll();

                int row = current[0];
                int col = current[1];

                // Destination reached
                if (row == n - 1 && col == n - 1) {
                    return pathLength;
                }

                for (int[] direction : directions) {

                    int newRow = row + direction[0];
                    int newCol = col + direction[1];

                    // Check boundaries
                    if (newRow < 0 || newRow >= n ||
                        newCol < 0 || newCol >= n) {
                        continue;
                    }

                    // Only visit open and unvisited cells
                    if (grid[newRow][newCol] == 0) {

                        grid[newRow][newCol] = 1;

                        queue.offer(new int[]{
                            newRow, newCol
                        });
                    }
                }
            }

            pathLength++;
        }

        return -1;
    }
}