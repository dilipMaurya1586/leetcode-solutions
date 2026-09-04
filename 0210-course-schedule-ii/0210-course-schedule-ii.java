import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        // 1. Create graph
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // 2. Calculate indegree
        int[] indegree = new int[numCourses];

        for (int[] prerequisite : prerequisites) {

            int course = prerequisite[0];
            int preCourse = prerequisite[1];

            graph.get(preCourse).add(course);

            indegree[course]++;
        }

        // 3. Put courses with no prerequisite into queue
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 4. Store answer
        int[] result = new int[numCourses];
        int index = 0;

        // 5. BFS
        while (!queue.isEmpty()) {

            int course = queue.poll();

            result[index++] = course;

            // Visit next courses
            for (int nextCourse : graph.get(course)) {

                indegree[nextCourse]--;

                // No more prerequisites
                if (indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        // 6. If all courses processed → valid order
        if (index == numCourses) {
            return result;
        }

        // Cycle exists
        return new int[0];
    }
}