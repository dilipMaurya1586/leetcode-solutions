class Solution {
    public int strangePrinter(String s) {

        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {

                int j = i + len - 1;
                dp[i][j] = dp[i + 1][j] + 1;

                for (int k = i + 1; k <= j; k++) {
                    if (s.charAt(i) == s.charAt(k)) {

                        int middel = 0;
                        if (k > i + 1) {
                            middel = dp[i + 1][k - 1];
                        }

                        dp[i][j] = Math.min(dp[i][j], middel + dp[k][j]);
                    }
                }

            }
        }
        return dp[0][n - 1];
    }
}