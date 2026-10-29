class Solution {
    public int getMaximumGenerated(int n) {

        if (n == 0) {
            return 0;
        }

        int[] num = new int[n+1];

        num[0] = 0;
        num[1] = 1;
        int sum = 1;

        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                num[i] = num[i / 2];
            } else {
                num[i] = num[i / 2] + num[i / 2 + 1];
            }
            sum = Math.max(sum, num[i]);
        }
        return sum;
    }
}