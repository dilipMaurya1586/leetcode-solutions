class Solution {
    public int minFallingPathSum(int[][] matrix) {

        int n = matrix.length;

        int[][] dp = new int[n][n];

        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = matrix[n - 1][j];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {

                int down = dp[i + 1][j];

                int left = Integer.MAX_VALUE;
                int right = Integer.MAX_VALUE;

                if (j > 0) {
                    left = dp[i + 1][j - 1];
                }

                if (j < n - 1) {
                    right = dp[i + 1][j + 1];
                }

                dp[i][j] = matrix[i][j] + Math.min(down, Math.min(left, right));

            }
        }

        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            ans = Math.min(ans, dp[0][j]);
        }
        return ans;
    }
}
