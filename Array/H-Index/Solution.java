//Version 0: O(nlgn) using definition
public class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        if (n == 0) return 0;
        Arrays.sort(citations);
        int i = 0;
        while (i < n && citations[i] < n-i) i++;
        return n-i;
    }
}

//Version 1: O(n) using bucket sort
//@copyright https://discuss.leetcode.com/topic/40765/java-bucket-sort-o-n-solution-with-detail-explanation/2
public class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        if (n == 0) return 0;
        int[] bucket = new int[n+1];
        for (int i = 0; i < n; i++) {
            if (citations[i] >= n) {
                bucket[n]++;
            } else {
                bucket[citations[i]]++;
            }
        }
        int count = 0;
        for (int i = n; i >= 0; i--) {
            count += bucket[i];
            if (count >= i) return i;
        }
        return 0;
    }
}

