class Solution {

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        List<List<Integer>> result = new ArrayList<>();

        List<Integer> path = new ArrayList<>();
        path.add(0);

        dfs(graph, 0, path, result);

        return result;
    }

    private void dfs(int[][] graph, int current, List<Integer> path, List<List<Integer>> result) {

        // Target reached
        if (current == graph.length - 1) {
            result.add(new ArrayList<>(path));
            return;
        }

        // Try every neighbor
        for (int neighbor : graph[current]) {

            // Choose
            path.add(neighbor);

            // Explore
            dfs(graph, neighbor, path, result);

            // Backtrack
            path.remove(path.size() - 1);
        }
    }
}