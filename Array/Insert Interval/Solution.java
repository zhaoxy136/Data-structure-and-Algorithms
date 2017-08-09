//Version 0:
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            res.add(newInterval);
            return res;
        }
        int start = newInterval.start, end = newInterval.end;
        int i;
        for (i = 0; i < intervals.size(); i++) {
            if (end < intervals.get(i).start) {
                res.add(new Interval(start, end));
                res.add(intervals.get(i));
                break;
            }
            if (end <= intervals.get(i).end && start >= intervals.get(i).start) {
                res.add(intervals.get(i));
                break;
            }
            if (start > intervals.get(i).end) {
                res.add(intervals.get(i));
            } else {
                start = Math.min(start, intervals.get(i).start);
                end = Math.max(end, intervals.get(i).end);
            }
        }
        if (i == intervals.size()) res.add(new Interval(start, end));
        while (++i < intervals.size()) {
            res.add(intervals.get(i));
        }
        return res;
    }
}
