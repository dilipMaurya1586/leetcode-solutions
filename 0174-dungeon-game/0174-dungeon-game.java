class Solution {
    public int calculateMinimumHP(int[][] dungeon) {

        int m = dungeon.length;
        int n = dungeon[0].length;

        int[][] dp = new int[m][n];

        dp[m - 1][n - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                if (i == m - 1 && j == n - 1) {
                    continue;
                }

                 int down = Integer.MAX_VALUE;
                int right = Integer.MAX_VALUE;
               

                if (i + 1 < m) {
                    down = dp[i + 1][j];
                }

                if (j + 1 < n) {
                    right = dp[i][j + 1];
                }

                int need = Math.min(down, right);

                dp[i][j] = Math.max(1, need - dungeon[i][j]);

            }
        }
        return dp[0][0];
    }
}
