/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals.size() == 0) return res;
        PriorityQueue<Interval> queue = new PriorityQueue<>(intervals.size(), new Comparator<Interval>(){
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        for (Interval v : intervals) {
            queue.add(v);
        }
        Interval prev = queue.poll();
        while (!queue.isEmpty()) {
            Interval tmp = queue.poll();
            if (tmp.start <= prev.end) {
                prev = new Interval(prev.start, Math.max(prev.end, tmp.end));
            } else {
                res.add(prev);
                prev = tmp;
            }
        }
        res.add(prev);
        return res;
    }
}
