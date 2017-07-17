/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
//Version 0: Sort + binary search
public class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        int[] res = new int[intervals.length];
        Map<Integer, Integer> map = new HashMap<>();
        int[] starts = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i].start;
            map.put(starts[i], i);
        }
        Arrays.sort(starts);
        for (int i = 0; i < intervals.length; i++) {
            res[i] = map.getOrDefault(search(starts, intervals[i].end), -1);
        }
        return res;
    }
    
    private int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                return nums[mid];
            }
        }
        return nums[start] >= target ? nums[start] : Integer.MAX_VALUE;
    }
}

//Version 1: TreeMap
public class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        int[] res = new int[intervals.length];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++) {
            map.put(intervals[i].start, i);
        }
        for (int i = 0; i < intervals.length; i++) {
            Integer key = map.ceilingKey(intervals[i].end);
            if (key != null) {
                res[i] = map.get(key);
            } else {
                res[i] = -1;
            }
        }
        return res;
    }
}
