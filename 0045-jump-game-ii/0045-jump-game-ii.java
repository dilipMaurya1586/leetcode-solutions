class Solution {
    public int jump(int[] nums) {

        int jumps = 0;
        int currentEnd = 0;
        int farthrst = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthrst = Math.max(farthrst, i + nums[i]);

            if (i == currentEnd) {
                jumps++;
                currentEnd = farthrst;
            }
        }
        return jumps;
    }
}