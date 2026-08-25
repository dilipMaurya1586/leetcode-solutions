class Solution {

    public List<List<Integer>> combinationSum2(
            int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(candidates);

        backtrack(0, candidates, target, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(
            int index,
            int[] candidates,
            int target,
            List<Integer> current,
            List<List<Integer>> result) {

        // Target achieved
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = index; i < candidates.length; i++) {

            // Skip duplicate elements at same level
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // Since array is sorted
            // no need to continue further
            if (candidates[i] > target) {
                break;
            }

            // Choose
            current.add(candidates[i]);

            // Explore
            backtrack(i + 1, candidates, target - candidates[i], current, result);

            // Undo
            current.remove(current.size() - 1);
        }
    }
}