class Solution {
    public int lastStoneWeight(int[] stones) {

        PriorityQueue<Integer> maxHeap =
            new PriorityQueue<>(Collections.reverseOrder());

        // Add all stones
        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        // Smash two heaviest stones
        while (maxHeap.size() > 1) {

            int y = maxHeap.poll(); // largest
            int x = maxHeap.poll(); // second largest

            if (y != x) {
                maxHeap.offer(y - x);
            }
        }

        // If no stone remains
        if (maxHeap.isEmpty()) {
            return 0;
        }

        return maxHeap.peek();
    }
}