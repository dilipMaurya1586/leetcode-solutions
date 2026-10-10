
import java.util.*;

class Solution {

    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        Queue<int[]> queue = new LinkedList<>();
        boolean found = false;

        // Step 1: Find and mark the first island using DFS
        for (int r = 0; r < n && !found; r++) {
            for (int c = 0; c < n && !found; c++) {
                if (grid[r][c] == 1) {
                    dfs(grid, r, c, queue);
                    found = true;
                }
            }
        }

        // Step 2: BFS outward from the first island
        int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int row = cell[0];
                int col = cell[1];

                for (int[] dir : directions) {
                    int nr = row + dir[0];
                    int nc = col + dir[1];

                    if (nr < 0 || nr >= n ||
                        nc < 0 || nc >= n) {
                        continue;
                    }

                    if (grid[nr][nc] == 1) {
                        return steps;
                    }

                    if (grid[nr][nc] == 0) {
                        grid[nr][nc] = 2;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }

            steps++;
        }

        return -1;
    }

    private void dfs(int[][] grid, int r, int c,
                     Queue<int[]> queue) {

        int n = grid.length;

        if (r < 0 || r >= n || c < 0 || c >= n ||
            grid[r][c] != 1) {
            return;
        }

        // Mark first-island land as 2
        grid[r][c] = 2;
        queue.offer(new int[]{r, c});

        dfs(grid, r - 1, c, queue);
        dfs(grid, r + 1, c, queue);
        dfs(grid, r, c - 1, queue);
        dfs(grid, r, c + 1, queue);
    }
}