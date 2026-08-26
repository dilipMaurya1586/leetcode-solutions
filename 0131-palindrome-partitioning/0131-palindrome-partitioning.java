class Solution {

    public List<List<String>> partition(String s) {

        List<List<String>> result = new ArrayList<>();

        backtrack(
            s,
            0,
            new ArrayList<>(),
            result
        );

        return result;
    }

    private void backtrack(
            String s,
            int start,
            List<String> current,
            List<List<String>> result) {

        // Entire string is partitioned
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Try every possible substring
        for (int end = start; end < s.length(); end++) {

            // Check if s[start...end] is palindrome
            if (!isPalindrome(s, start, end)) {
                continue;
            }

            // Choose
            current.add(s.substring(start, end + 1));

            // Explore remaining string
            backtrack(
                s,
                end + 1,
                current,
                result
            );

            // Undo
            current.remove(current.size() - 1);
        }
    }

    private boolean isPalindrome(
            String s,
            int left,
            int right) {

        while (left < right) {

            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}