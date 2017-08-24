//Version 0: original
class HitCounter {
    int[] hits;
    int lastVisit;
    int count;
    /** Initialize your data structure here. */
    public HitCounter() {
        this.hits = new int[300];
        this.lastVisit = 1;
        this.count = 0;
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        update(timestamp);
        int index = (timestamp - 1) % 300;
        hits[index]++;
        count++;
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        update(timestamp);
        return count;
    }
    
    private void update(int timestamp) {
        if (timestamp - lastVisit >= 300) {
            Arrays.fill(hits, 0);
            count = 0;
        } else {
            for (int i = lastVisit + 1; i <= timestamp; i++) {
                int index = (i - 1) % 300;
                count -= hits[index];
                hits[index] = 0;
            }
        }
        lastVisit = timestamp;
    }
}

//Version 1: more elegant
//@copyright:https://discuss.leetcode.com/topic/48758/super-easy-design-o-1-hit-o-s-gethits-no-fancy-data-structure-is-needed/2
class HitCounter {
    int[] hits;
    int[] lastVisit;
    /** Initialize your data structure here. */
    public HitCounter() {
        hits = new int[300];
        lastVisit = new int[300];
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int index = timestamp % 300;
        if (lastVisit[index] == timestamp) {
            hits[index]++;
        } else {
            lastVisit[index] = timestamp;
            hits[index] = 1;
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int count = 0;
        for (int i = 0; i < 300; i++) {
            if (timestamp - lastVisit[i] < 300) {
                count += hits[i];
            }
        }
        return count;
    }
}
