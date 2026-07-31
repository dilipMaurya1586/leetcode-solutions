class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        int[] count = new int[128];
        for (char ch : t.toCharArray()) {
            count[ch]++;
        }
        int left = 0;
        int right = 0;
        int required = t.length();
        int minLength = Integer.MAX_VALUE;
        int start = 0;
        while (right < s.length()) {
            char ch = s.charAt(right);
            if (count[ch] > 0) {
                required--;
            }
            count[ch]--;
            right++;

            while (required == 0) {
                if (right - left < minLength) {
                    minLength = right - left;
                    start = left;
                }
                char leftChar = s.charAt(left);
                count[leftChar]++;
                if (count[leftChar] > 0) {
                    required++;
                }
                left++;

            }
        }
        return minLength == Integer.MAX_VALUE ? "" : s.substring(start, start + minLength);
    }

}