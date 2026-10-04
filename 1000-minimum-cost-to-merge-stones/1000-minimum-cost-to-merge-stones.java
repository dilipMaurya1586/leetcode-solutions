class Solution {
    public int mergeStones(int[] stones, int k) {

        int n = stones.length;

        if ((n - 1) % (k - 1) != 0) {
            return -1;
        }

        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stones[i];
        }

        int[][][] dp = new int[n][n][k + 1];

        for (int[][] arr : dp) {
            for (int[] row : arr) {
                Arrays.fill(row, 1_000_000_000);
            }
        }

        for (int i = 0; i < n; i++) {
            dp[i][i][1] = 0;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {

                int j = i + len - 1;

                for (int piles = 2; piles <= k; piles++) {

                    for (int mid = i; mid < j; mid += k - 1) {

                        dp[i][j][piles] = Math.min(
                            dp[i][j][piles],
                            dp[i][mid][1] + dp[mid + 1][j][piles - 1]
                        );
                    }
                }

                if (dp[i][j][k] < 1_000_000_000) {
                    dp[i][j][1] = dp[i][j][k]
                        + prefix[j + 1] - prefix[i];
                }
            }
        }

        return dp[0][n - 1][1];
    }
}