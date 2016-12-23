//Overwriting the interface: Comparator  http://blog.csdn.net/yongh701/article/details/44131051
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
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval o1, Interval o2) {
                return o1.end - o2.end;
            }
        });
        int num = 1;
        int j = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start >= intervals[j].end) {
                num++;
                j = i;
            }
        }
        return intervals.length - num;
    }
}
