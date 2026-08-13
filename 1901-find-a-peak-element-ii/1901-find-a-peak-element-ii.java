class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        int left = 0;
        int right = cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Find maximum element in this column
            int maxRow = 0;

            for (int r = 1; r < rows; r++) {
                if (mat[r][mid] > mat[maxRow][mid]) {
                    maxRow = r;
                }
            }

            int leftValue = mid > 0 ? mat[maxRow][mid - 1] : -1;
            int rightValue = mid < cols - 1 ? mat[maxRow][mid + 1] : -1;

            // Current cell is a peak
            if (mat[maxRow][mid] > leftValue &&
                mat[maxRow][mid] > rightValue) {

                return new int[]{maxRow, mid};
            }

            // Move towards larger neighbor
            if (leftValue > mat[maxRow][mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return new int[]{-1, -1};
    }
}