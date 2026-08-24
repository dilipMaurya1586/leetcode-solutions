class MedianFinder {

    PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());

    PriorityQueue<Integer> large = new PriorityQueue<>();

    public MedianFinder() {

    }

    public void addNum(int num) {
        small.offer(num);
        if (!small.isEmpty() && !large.isEmpty() && small.peek() > large.peek()) {
            int value = small.poll();
            large.offer(value);
        }
        if (small.size() > large.size() + 1) {
            large.offer(small.poll());
        } else if (large.size() > small.size()) {
            small.offer(large.poll());
        }
    }

    public double findMedian() {
        if(small.size() > large.size()) {
            return small.peek();
        }
        return (small.peek() + large.peek()) / 2.0;
    }
}
