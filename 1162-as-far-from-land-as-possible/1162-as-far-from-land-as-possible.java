import java.util.*;

class Solution {

    public int maxDistance(int[][] grid) {

        int n = grid.length;

        Queue<int[]> queue = new LinkedList<>();

        // Add all land cells
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                if (grid[row][col] == 1) {
                    queue.offer(new int[]{row, col});
                }
            }
        }

        // If all cells are land or all cells are water
        if (queue.isEmpty() || queue.size() == n * n) {
            return -1;
        }

        int distance = -1;

        int[][] directions = {
            {-1, 0},  // up
            {1, 0},   // down
            {0, -1},  // left
            {0, 1}    // right
        };

        while (!queue.isEmpty()) {

            int size = queue.size();

            distance++;

            for (int i = 0; i < size; i++) {

                int[] current = queue.poll();

                int row = current[0];
                int col = current[1];

                for (int[] direction : directions) {

                    int newRow = row + direction[0];
                    int newCol = col + direction[1];

                    if (newRow < 0 || newRow >= n ||
                        newCol < 0 || newCol >= n) {
                        continue;
                    }

                    if (grid[newRow][newCol] == 0) {

                        // Mark as visited
                        grid[newRow][newCol] = 1;

                        queue.offer(new int[]{
                            newRow, newCol
                        });
                    }
                }
            }
        }

        return distance;
    }
}