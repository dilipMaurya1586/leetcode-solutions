class Solution {
    public int leastInterval(char[] tasks, int n) {

        int[] freq = new int[26];
        for(char task : tasks) {
            freq[task - 'A']++;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue (Collections.reverseOrder());

        for(int f : freq) {
            if(f > 0) {
                maxHeap.offer(f);
            }
        }
        Queue<int[]>queue = new LinkedList<>();
        int time = 0;
        while(!maxHeap.isEmpty() || !queue.isEmpty()) {
            time++;
            if(!maxHeap.isEmpty()) {
                int count = maxHeap.poll();
                count--;
                if(count > 0 ) {
                    queue.offer(new int[]{count, time + n});
                }
            }
            if(!queue.isEmpty() && queue.peek()[1] == time ) {
                    maxHeap.offer(queue.poll()[0]);
            }
        }
        return time;
    }
}