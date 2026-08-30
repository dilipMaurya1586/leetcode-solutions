class Solution {

    public int maxAreaOfIsland(int[][] grid) {

        int maxArea = 0;

        for (int row = 0; row < grid.length; row++) {

            for (int col = 0; col < grid[0].length; col++) {

                if (grid[row][col] == 1) {

                    int area = dfs(grid, row, col);

                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }

    private int dfs(int[][] grid, int row, int col) {

        // Boundary / water check
        if (row < 0 || row >= grid.length ||
            col < 0 || col >= grid[0].length ||
            grid[row][col] == 0) {

            return 0;
        }

        // Mark visited
        grid[row][col] = 0;

        // Count current cell
        int area = 1;

        // Four directions
        area += dfs(grid, row - 1, col); // up
        area += dfs(grid, row + 1, col); // down
        area += dfs(grid, row, col - 1); // left
        area += dfs(grid, row, col + 1); // right

        return area;
    }
}