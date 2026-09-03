class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // Create adjacency list
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Build directed graph
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int preCourse = prerequisite[1];

            graph.get(preCourse).add(course);
        }

        // 0 = not visited
        // 1 = currently in DFS path
        // 2 = completely processed
        int[] state = new int[numCourses];

        // Check every course
        for (int i = 0; i < numCourses; i++) {

            if (state[i] == 0) {
                if (hasCycle(graph, i, state)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean hasCycle(List<List<Integer>> graph,
                             int course,
                             int[] state) {

        // Already in current DFS path → cycle
        if (state[course] == 1) {
            return true;
        }

        // Already completely processed → no cycle
        if (state[course] == 2) {
            return false;
        }

        // Mark as currently visiting
        state[course] = 1;

        // Visit all neighboring courses
        for (int nextCourse : graph.get(course)) {

            if (hasCycle(graph, nextCourse, state)) {
                return true;
            }
        }

        // DFS completed for this course
        state[course] = 2;

        return false;
    }
}