public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        int res = 0;
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        for (int i = 0; i < intervals.length; i++) {
            if (!queue.isEmpty() && intervals[i].start >= queue.peek()) {
                queue.poll();
            }
            queue.offer(intervals[i].end);
        }
        return queue.size();
    }
}
