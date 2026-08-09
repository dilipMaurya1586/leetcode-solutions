class Solution {
    public int nextGreaterElement(int n) {

        char[] digits = String.valueOf(n).toCharArray();

        //step-1: first find decresion digits from right
        int i = digits.length - 2;
        while (i >= 0 && digits[i] >= digits[i + 1]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }


        // 2. Find digit just greater than digits[i]
        int j = digits.length - 1;

        while (digits[j] <= digits[i]) {
            j--;
        }


        // 3. Swap
        char temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;

        //step-4: reverse remaing digits
        int left = i + 1;
        int right = digits.length - 1;
        while (left < right) {
            temp = digits[left];
            digits[left] = digits[right];
            digits[right] = temp;
            left++;
            right--;
        }

        //step-5: cover to long to avoid overflow
        long result = Long.parseLong(new String(digits));
        if (result > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) result;

    }
}