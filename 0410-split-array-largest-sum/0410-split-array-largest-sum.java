class Solution {
    public int splitArray(int[] nums, int k) {

        int left = 0;
        int right = 0;

        for(int num : nums) {
            left =  Math.max(left, num);
            right += num;
        }

        while(left < right) {
            int mid =  left + (right - left) / 2;
            int subarrays = 1;
            int sum = 0;

            for(int num : nums) {
                if(sum + num > mid) {
                    subarrays++;
                    sum = num;
                } else {
                    sum += num;
                }
            }
            if(subarrays <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        } 
        return left;
    }
}