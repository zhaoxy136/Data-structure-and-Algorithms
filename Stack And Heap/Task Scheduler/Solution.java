//Version 0: heap, greedy
public class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char ch : tasks) {
            map[ch - 'A']++;
        }
        PriorityQueue<Integer> freq = new PriorityQueue<>((a, b) -> (b-a));
        for (int i : map) {
            if (i > 0) freq.add(i);
        }
        int count = 0;
        while (!freq.isEmpty()) {
            int interval = n + 1;
            List<Integer> wait = new ArrayList<>();
            while (interval > 0 && !freq.isEmpty()) {
                int tmp = freq.poll();
                if (tmp > 1) {
                    wait.add(tmp-1);
                }
                count++;
                interval--;
            }
            for (int i : wait) {
                freq.add(i);
            }
            if (freq.isEmpty()) break;
            count += interval;
            
        }
        return count;
    }
}

//Version 1:
public class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c : tasks) {
            map[c-'A']++;
        }
        Arrays.sort(map);
        int i = 24;
        while (i >= 0 && map[i] == map[25]) i--;
        return Math.max(tasks.length, (map[25] - 1) * (n + 1) + 25 - i);
    }
}
