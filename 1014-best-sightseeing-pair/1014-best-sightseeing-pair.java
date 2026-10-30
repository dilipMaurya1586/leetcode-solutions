class Solution {
    public int maxScoreSightseeingPair(int[] values) {

        int maxLeft = values[0] + 0;
        int maxScore = 0;

        for(int i=1; i<values.length; i++) {

                int score = maxLeft + values[i] - i;
                maxScore = Math.max(maxScore, score);
                maxLeft = Math.max(maxLeft, values[i] + i);
        }
        return maxScore;
    }
}