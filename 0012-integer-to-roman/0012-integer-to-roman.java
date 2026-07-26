class Solution {
    public String intToRoman(int num) {

        int[] value = {
                // "1000" , "500", "100","50", "10",  "1" 
                1000, 900, 500, 400,
                100, 90, 50, 40,
                10, 9, 5, 4, 1
        };

        String[] symbole = {
                // "M", "D", "C", "L", "X", "I"
                "M", "CM", "D", "CD",
                "C", "XC", "L", "XL",
                "X", "IX", "V", "IV", "I"
        };

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < value.length; i++) {
            while (num >= value[i]) {
                num -= value[i];
                result.append(symbole[i]);
            }
        }
        return result.toString();
    }
}