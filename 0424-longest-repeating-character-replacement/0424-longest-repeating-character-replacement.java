class Solution {
    public int characterReplacement(String s, int k) {
        int[] fre = new int[26];
        int left = 0;
        int maxFre = 0;
        int maxLength = 0;
        for (int right = 0; right < s.length(); right++) {
            fre[s.charAt(right) - 'A']++;
            maxFre = Math.max(maxFre, fre[s.charAt(right) - 'A']);
            while ((right - left + 1) - maxFre > k) {
                fre[s.charAt(left) - 'A']--;
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}