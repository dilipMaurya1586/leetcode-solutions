class Solution {
    public int countPalindromicSubsequences(String s) {

        int n = s.length();
        int MOD = 1_000_000_007;

        // dp[i][j][k]
        // k = 0 -> a
        // k = 1 -> b
        // k = 2 -> c
        // k = 3 -> d
        long[][][] dp = new long[n][n][4];

        // Length = 1
        for (int i = 0; i < n; i++) {
            dp[i][i][s.charAt(i) - 'a'] = 1;
        }

        // Length = 2 to n
        for (int len = 2; len <= n; len++) {

            for (int i = 0; i + len <= n; i++) {

                int j = i + len - 1;

                for (char ch = 'a'; ch <= 'd'; ch++) {

                    int k = ch - 'a';

                    if (s.charAt(i) == ch && s.charAt(j) == ch) {

                        dp[i][j][k] =
                            2
                            + dp[i + 1][j - 1][0]
                            + dp[i + 1][j - 1][1]
                            + dp[i + 1][j - 1][2]
                            + dp[i + 1][j - 1][3];

                        dp[i][j][k] %= MOD;

                    } else if (s.charAt(i) == ch) {

                        dp[i][j][k] = dp[i][j - 1][k];

                    } else if (s.charAt(j) == ch) {

                        dp[i][j][k] = dp[i + 1][j][k];

                    } else {

                        dp[i][j][k] = dp[i + 1][j - 1][k];
                    }
                }
            }
        }

        long answer = 0;

        for (int k = 0; k < 4; k++) {
            answer += dp[0][n - 1][k];
        }

        return (int) (answer % MOD);
    }
}