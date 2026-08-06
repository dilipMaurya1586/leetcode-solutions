class Solution {
    public int[] secondGreaterElement(int[] nums) {

        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        Stack<Integer> temp = new Stack<>();

        for(int i=0; i<n; i++) {
            while(!stack2.isEmpty() && nums[i] > nums[stack2.peek()] ) {
                ans[stack2.pop()] = nums[i];
            }
            while(!stack1.isEmpty() && nums[i] > nums[stack1.peek()]) {
                temp.push(stack1.pop());
            }
            while(!temp.isEmpty()) {
                stack2.push(temp.pop());
            }
            stack1.push(i);
        }
        return ans;

    }
}