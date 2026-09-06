import java.util.*;

class Solution {

    public int ladderLength(String beginWord,
                            String endWord,
                            List<String> wordList) {

        // End word must exist
        if (!wordList.contains(endWord)) {
            return 0;
        }

        Set<String> words = new HashSet<>(wordList);

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        int level = 1;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                String current = queue.poll();

                // Change every character
                char[] chars = current.toCharArray();

                for (int j = 0; j < chars.length; j++) {

                    char original = chars[j];

                    for (char ch = 'a'; ch <= 'z'; ch++) {

                        if (ch == original) {
                            continue;
                        }

                        chars[j] = ch;

                        String nextWord = new String(chars);

                        // Found target
                        if (nextWord.equals(endWord)) {
                            return level + 1;
                        }

                        // Valid and not visited
                        if (words.contains(nextWord)) {
                            queue.offer(nextWord);
                            words.remove(nextWord);
                        }
                    }

                    // Restore original character
                    chars[j] = original;
                }
            }

            level++;
        }

        return 0;
    }
}