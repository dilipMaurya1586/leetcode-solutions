
class Solution {
    private static final long MOD = 1_000_000_007L;

    private long[] sum;
    private long[] squareSum;
    private int[] lazy;
    private int n;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        squareSum = new long[4 * n];
        lazy = new int[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int previous = last[num];

            update(1, 0, n - 1, previous + 1, i);

            last[num] = i;
            ans = (ans + squareSum[1]) % MOD;
        }

        return (int) ans;
    }

    private void apply(int node, int left, int right, int delta) {
        long oldSum = sum[node];
        long len = right - left + 1L;
        long d = delta;

        squareSum[node] = (
            squareSum[node]
            + 2L * d * oldSum
            + len * d * d
        ) % MOD;

        sum[node] = (oldSum + len * d) % MOD;
        lazy[node] += delta;
    }

    private void pushDown(int node, int left, int right) {
        if (lazy[node] == 0 || left == right) {
            return;
        }

        int mid = left + (right - left) / 2;
        int pending = lazy[node];

        apply(node * 2, left, mid, pending);
        apply(node * 2 + 1, mid + 1, right, pending);

        lazy[node] = 0;
    }

    private void update(
        int node, int left, int right, int ql, int qr
    ) {
        if (ql > right || qr < left) {
            return;
        }

        if (ql <= left && right <= qr) {
            apply(node, left, right, 1);
            return;
        }

        pushDown(node, left, right);

        int mid = left + (right - left) / 2;

        update(node * 2, left, mid, ql, qr);
        update(node * 2 + 1, mid + 1, right, ql, qr);

        sum[node] = (
            sum[node * 2] + sum[node * 2 + 1]
        ) % MOD;

        squareSum[node] = (
            squareSum[node * 2] + squareSum[node * 2 + 1]
        ) % MOD;
    }
}
