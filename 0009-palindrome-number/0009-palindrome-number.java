class Solution {
    public boolean isPalindrome(int x) {

        //base case
        if(x<0 || x % 10 == 0 && x != 0) {
            return false ;
        }

        int reversedHalf = 0;

        // divide reversedHalf
        while(x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + x % 10;
            x /= 10;
        }

        //even length
        //odd length
        return x == reversedHalf || x == reversedHalf / 10;
        
    }
}