class Solution {

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(
            int index,
            int[] nums,
            List<Integer> current,
            List<List<Integer>> result) {

        // Every current combination is a valid subset
        result.add(new ArrayList<>(current));

        for (int i = index; i < nums.length; i++) {

            // Take nums[i]
            current.add(nums[i]);
            // Move forward
            backtrack(i + 1, nums, current, result);
            // Undo the choice
            current.remove(current.size() - 1);
        }
    }
}