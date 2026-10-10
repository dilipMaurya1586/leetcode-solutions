
import java.util.*;

class Solution {
    public int openLock(String[] deadends, String target) {

        Set<String> dead = new HashSet<>(Arrays.asList(deadends));

        if (dead.contains("0000")) return -1;
        if (target.equals("0000")) return 0;

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer("0000");
        visited.add("0000");

        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                if (current.equals(target)) {
                    return moves;
                }

                for (int j = 0; j < 4; j++) {
                    char[] digits = current.toCharArray();
                    char original = digits[j];

                    // Turn one wheel forward
                    digits[j] = original == '9' ? '0' : (char)(original + 1);
                    String next = new String(digits);

                    if (!dead.contains(next) && !visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }

                    // Turn the same wheel backward
                    digits[j] = original == '0' ? '9' : (char)(original - 1);
                    next = new String(digits);

                    if (!dead.contains(next) && !visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}