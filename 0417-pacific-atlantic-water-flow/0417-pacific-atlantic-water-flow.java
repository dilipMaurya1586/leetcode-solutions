class Solution {

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        // Pacific: top row
        for (int col = 0; col < cols; col++) {
            dfs(heights, pacific, 0, col);
        }

        // Pacific: left column
        for (int row = 0; row < rows; row++) {
            dfs(heights, pacific, row, 0);
        }

        // Atlantic: bottom row
        for (int col = 0; col < cols; col++) {
            dfs(heights, atlantic, rows - 1, col);
        }

        // Atlantic: right column
        for (int row = 0; row < rows; row++) {
            dfs(heights, atlantic, row, cols - 1);
        }

        List<List<Integer>> result = new ArrayList<>();

        // Find common cells
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                if (pacific[row][col] && atlantic[row][col]) {

                    result.add(Arrays.asList(row, col));
                }
            }
        }

        return result;
    }

    private void dfs(
        int[][] heights,
        boolean[][] visited,
        int row,
        int col
    ) {

        visited[row][col] = true;

        int[][] directions = {
            {-1, 0},  // up
            {1, 0},   // down
            {0, -1},  // left
            {0, 1}    // right
        };

        for (int[] dir : directions) {

            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Boundary check
            if (newRow < 0 || newRow >= heights.length ||
                newCol < 0 || newCol >= heights[0].length) {
                continue;
            }

            // Already visited
            if (visited[newRow][newCol]) {
                continue;
            }

            // Reverse flow:
            // next cell must be same height or higher
            if (heights[newRow][newCol] < heights[row][col]) {
                continue;
            }

            dfs(heights, visited, newRow, newCol);
        }
    }
}