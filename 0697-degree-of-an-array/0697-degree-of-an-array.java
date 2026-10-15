import java.util.*;

class Solution {
    public int findShortestSubArray(int[] nums) {

        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Integer> first = new HashMap<>();

        int degree = 0;
        int minLength = nums.length;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            first.putIfAbsent(num, i);
            count.put(num, count.getOrDefault(num, 0) + 1);

            degree = Math.max(degree, count.get(num));
        }

        for (int num : count.keySet()) {
            if (count.get(num) == degree) {
                int length = nums.length; // replaced below
                length = getLastIndex(nums, num) - first.get(num) + 1;
                minLength = Math.min(minLength, length);
            }
        }

        return minLength;
    }

    private int getLastIndex(int[] nums, int target) {
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }
}