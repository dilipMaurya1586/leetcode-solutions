class Solution {
    public int removeDuplicates(int[] arr) {
        HashSet<Integer> s  = new HashSet<>();
        int idx = 0;
        for(int i=0; i<arr.length; i++) {
            if(!s.contains(arr[i])) {
                s.add(arr[i]);
                arr[idx++] = arr[i];
            }
        }
        return idx;

    }
}