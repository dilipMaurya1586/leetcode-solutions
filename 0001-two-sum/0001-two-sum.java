class Solution {
    public int[] twoSum(int[] nums, int target) {

        // Map<Integer, Integer> map = new HashMap<>();

        // for (int i = 0; i < nums.length; i++) {
          
        //     int need = target - nums[i];
         
        //     if (map.containsKey(need)) {
        //         return new int[] { map.get(need), i };
        //     }
        //     map.put(nums[i], i);
     
        // }
        // return new int[] {};

        for(int i=0; i<nums.length; i++) {
            for(int j=i+1; j<nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{};
    }
}