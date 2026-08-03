class Solution {
    public double[] getCollisionTimes(int[][] cars) {
        int n = cars.length;
        double[] ans = new double[n];
        Arrays.fill(ans, -1.0);
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty()) {
                int j = stack.peek();
                if (cars[i][1] <= cars[j][1]) {
                    stack.pop();
                    continue;
                }
                double time = (double) (cars[j][0] - cars[i][0]) / (cars[i][1] - cars[j][1]);
                if (ans[j] == -1.0 || time <= ans[j]) {
                    ans[i] = time;
                    break;
                }
                stack.pop();
            }
            stack.push(i);
        }
        return ans;
    }
}