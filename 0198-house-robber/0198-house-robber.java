class Solution {
    public int rob(int[] nums) {

      int prev1 = 0; // dp[i-1]
        int prev2 = 0; // dp[i-2]

        for (int money : nums) {
            int temp = prev1;
            prev1 = Math.max(prev1, money + prev2);
            prev2 = temp;
        }

        return prev1;

        // int n = nums.length;

        // if (n == 0)
        //     return 0;
        // if (n == 1)
        //     return nums[0];

        // int[] dp = new int[n];
        // dp[0] = nums[0];
        // dp[1] = Math.max(nums[0], nums[1]);

        // for (int i = 2; i < n; i++) {
        //     dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        // }
        // return dp[n - 1];

    }
}