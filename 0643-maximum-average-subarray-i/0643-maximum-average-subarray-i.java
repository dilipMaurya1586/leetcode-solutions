class Solution {
    public double findMaxAverage(int[] nums, int k) {

        // First window ka sum
        int windowSum = 0;

        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }

        int maxSum = windowSum;

        // Sliding Window
        for (int i = k; i < nums.length; i++) {

            // Old element remove
            windowSum -= nums[i - k];

            // New element add
            windowSum += nums[i];

            // Maximum sum update
            maxSum = Math.max(maxSum, windowSum);
        }

        return (double) maxSum / k;
    }
}