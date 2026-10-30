
class Solution {
    public int maxTurbulenceSize(int[] arr) {

        int u = 1;
        int d = 1;
        int mL = 1;

        for(int i=1; i<arr.length; i++) {

            if(arr[i] > arr[i-1]) {
                u = d + 1;
                d= 1;
            } else if(arr[i] < arr[i-1]) {
                d = u + 1;
                u = 1;
            } else {
                u = 1;
                d = 1;

            }
            mL = Math.max(mL, Math.max(u, d));

        }
        return mL;

    }
}
