class Solution {
    public int findLengthOfLCIS(int[] nums) {

        if(nums.length == 0) {
            return 0;
        }        

        int maxLength = 1;
        int count = 1;

        for(int i=0; i<nums.length - 1; i++) {
            if(nums[i] < nums[i+1]) {
                count++;
            } else {
                count = 1;
            }
            maxLength = Math.max(maxLength, count);
        }
        return maxLength;
    }
}