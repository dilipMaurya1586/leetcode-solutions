
class Solution {
    public int findPaths(int m, int n, int maxMove,
                         int startRow, int startColumn) {

        int MOD = 1_000_000_007;

        int[][] dp = new int[m][n];

        dp[startRow][startColumn] = 1;

        int result = 0;

        int[][] directions = {
            {-1, 0},  // UP
            {1, 0},   // DOWN
            {0, -1},  // LEFT
            {0, 1}    // RIGHT
        };

        for (int move = 1; move <= maxMove; move++) {

            int[][] next = new int[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    if (dp[i][j] == 0) {
                        continue;
                    }

                    for (int[] dir : directions) {

                        int nr = i + dir[0];
                        int nc = j + dir[1];

                        if (nr < 0 || nr >= m ||
                            nc < 0 || nc >= n) {

                            result = (result + dp[i][j]) % MOD;

                        } else {

                            next[nr][nc] =
                                (next[nr][nc] + dp[i][j]) % MOD;
                        }
                    }
                }
            }

            dp = next;
        }

        return result;
    }
}
