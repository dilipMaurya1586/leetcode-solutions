class Solution {

    public void solve(char[][] board) {

        int rows = board.length;
        int cols = board[0].length;

        // 1. Check left and right boundaries
        for (int row = 0; row < rows; row++) {

            if (board[row][0] == 'O') {
                dfs(board, row, 0);
            }

            if (board[row][cols - 1] == 'O') {
                dfs(board, row, cols - 1);
            }
        }

        // 2. Check top and bottom boundaries
        for (int col = 0; col < cols; col++) {

            if (board[0][col] == 'O') {
                dfs(board, 0, col);
            }

            if (board[rows - 1][col] == 'O') {
                dfs(board, rows - 1, col);
            }
        }

        // 3. Convert remaining O → X
        //    Convert SAFE → O
        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < cols; col++) {

                if (board[row][col] == 'O') {

                    board[row][col] = 'X';

                } else if (board[row][col] == 'S') {

                    board[row][col] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int row, int col) {

        // Boundary check
        if (row < 0 || row >= board.length ||
            col < 0 || col >= board[0].length ||
            board[row][col] != 'O') {

            return;
        }

        // Mark as SAFE
        board[row][col] = 'S';

        // Up
        dfs(board, row - 1, col);

        // Down
        dfs(board, row + 1, col);

        // Left
        dfs(board, row, col - 1);

        // Right
        dfs(board, row, col + 1);
    }
}