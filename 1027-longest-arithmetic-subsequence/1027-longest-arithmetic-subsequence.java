class Solution {
    public int longestArithSeqLength(int[] nums) {

        int n = nums.length;
        int ans = 2;

        HashMap<Integer, Integer>[] dp = new HashMap[n];

        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {

                int diff = nums[i] - nums[j];

                int length = dp[j].getOrDefault(diff, 1) + 1;

                dp[i].put(diff, length);

                ans = Math.max(ans, length);
            }
        }

        return ans;
    }
}