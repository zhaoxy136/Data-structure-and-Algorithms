//Version 0: original and STUPID!
public class Solution {
    public int findMinDifference(List<String> timePoints) {
        int res = Integer.MAX_VALUE;
        TreeSet<Integer> set = new TreeSet<>();
        for (String str : timePoints) {
            int time = getMin(str);
            if (set.contains(time)) {
                return 0;
            }
            if (set.lower(time) != null) {
                res = Math.min(res, time-set.lower(time));
            }
            if (set.higher(time) != null) {
                res = Math.min(res, set.higher(time)-time);
            }
            set.add(time);
            if (time < 720) {
                if (set.lower(time+1440) != null) {
                    res = Math.min(res, time+1440-set.lower(time+1440));
                }
                if (set.higher(time+1440) != null) {
                    res = Math.min(res, set.higher(time+1440)-time+1440);
                }
                set.add(time+1440);
            }
        }
        return res;
    }
    
    private int getMin(String s) {
        String[] time= s.split(":");
        int hour = Integer.parseInt(time[0]);
        int mins = Integer.parseInt(time[1]);
        return hour * 60 + mins;
    }
}

//Version 1: normal but working
public class Solution {
    public int findMinDifference(List<String> timePoints) {
        int res = Integer.MAX_VALUE;
        int[] time = new int[timePoints.size()];
        for (int i = 0; i < timePoints.size(); i++) {
            time[i] = getMin(timePoints.get(i));
        }
        Arrays.sort(time);
        for (int i = 1; i < time.length; i++) {
            res = Math.min(res, time[i] - time[i-1]);
        }
        return Math.min(res, time[0]+1440-time[time.length-1]);
    }
    
    private int getMin(String s) {
        String[] time= s.split(":");
        int hour = Integer.parseInt(time[0]);
        int mins = Integer.parseInt(time[1]);
        return hour * 60 + mins;
    }
}

