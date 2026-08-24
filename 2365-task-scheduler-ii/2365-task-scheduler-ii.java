class Solution {
    public long taskSchedulerII(int[] tasks, int space) {
        
        Map<Integer, Long> map = new HashMap<>();
        long day = 0;
        for(int task : tasks) {
            day++;
            day = Math.max(day, map.getOrDefault(task, 0L));
            map.put(task, day + space + 1);
        }
        return day;
    }
}