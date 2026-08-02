class Solution {
    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList();
        backTracking(result, "", 0, 0, n);
        return result;

    }

    private void backTracking(List<String> result, String current, int open, int close, int n) {

        //base case
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }

        //add opining bracket
        if (open < n) {
            backTracking(result, current + "(", open + 1, close, n);
        }

        //add closing bracket
        if (close < open) {
            backTracking(result, current + ")", open, close + 1, n);
        }

    }

}