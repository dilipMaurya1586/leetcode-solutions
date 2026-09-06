import java.util.*;

class Solution {

    public List<List<String>> findLadders(
            String beginWord,
            String endWord,
            List<String> wordList) {

        List<List<String>> result = new ArrayList<>();

        Set<String> words = new HashSet<>(wordList);

        if (!words.contains(endWord)) {
            return result;
        }

        // parent map
        Map<String, List<String>> parents = new HashMap<>();

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        boolean found = false;

        while (!queue.isEmpty() && !found) {

            int size = queue.size();

            Set<String> levelVisited = new HashSet<>();

            for (int i = 0; i < size; i++) {

                String current = queue.poll();
                char[] chars = current.toCharArray();

                for (int j = 0; j < chars.length; j++) {

                    char original = chars[j];

                    for (char ch = 'a'; ch <= 'z'; ch++) {

                        if (ch == original) {
                            continue;
                        }

                        chars[j] = ch;

                        String next = new String(chars);

                        if (!words.contains(next)) {
                            continue;
                        }

                        if (levelVisited.contains(next)) {

                            parents.get(next).add(current);

                        } else if (!visited.contains(next)) {

                            queue.offer(next);
                            levelVisited.add(next);

                            parents
                                    .computeIfAbsent(next, k -> new ArrayList<>())
                                    .add(current);

                            if (next.equals(endWord)) {
                                found = true;
                            }
                        }
                    }

                    chars[j] = original;
                }
            }

            visited.addAll(levelVisited);
        }

        // Backtracking
        if (!found) {
            return result;
        }

        List<String> path = new ArrayList<>();
        path.add(endWord);

        backtrack(
                endWord,
                beginWord,
                parents,
                path,
                result);

        return result;
    }

    private void backtrack(
            String current,
            String beginWord,
            Map<String, List<String>> parents,
            List<String> path,
            List<List<String>> result) {

        if (current.equals(beginWord)) {

            List<String> sequence = new ArrayList<>(path);

            Collections.reverse(sequence);

            result.add(sequence);

            return;
        }

        for (String parent : parents.getOrDefault(
                current,
                Collections.emptyList())) {

            path.add(parent);

            backtrack(
                    parent,
                    beginWord,
                    parents,
                    path,
                    result);

            path.remove(path.size() - 1);
        }
    }
}