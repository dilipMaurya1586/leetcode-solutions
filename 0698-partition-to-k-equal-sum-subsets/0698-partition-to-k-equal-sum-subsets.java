class Solution {

    public boolean canPartitionKSubsets(int[] nums, int k) {

        int total = 0;

        for (int num : nums) {
            total += num;
        }

        if (total % k != 0) {
            return false;
        }

        int target = total / k;
        int n = nums.length;

        // If any number is greater than target
        for (int num : nums) {
            if (num > target) {
                return false;
            }
        }

        int[] dp = new int[1 << n];

        // -1 means this state has not been calculated
        Arrays.fill(dp, -1);

        dp[0] = 0;

        for (int mask = 0; mask < (1 << n); mask++) {

            if (dp[mask] == -1) {
                continue;
            }

            for (int i = 0; i < n; i++) {

                // Already used
                if ((mask & (1 << i)) != 0) {
                    continue;
                }

                // Adding this number exceeds target
                if (dp[mask] + nums[i] > target) {
                    continue;
                }

                int newMask = mask | (1 << i);

                dp[newMask] = (dp[mask] + nums[i]) % target;
            }
        }

        return dp[(1 << n) - 1] == 0;
    }
}