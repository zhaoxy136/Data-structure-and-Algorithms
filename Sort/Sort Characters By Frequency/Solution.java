//Version 0: bucket sort
class Solution {
    public String frequencySort(String s) {
        if (s == null || s.length() == 0) return s;
        int[] map = new int[256];
        int max = 0;
        for (char c : s.toCharArray()) {
            map[c]++;
            max = Math.max(max, map[c]);
        }
        List<Character>[] buckets = new List[max+1];
        for (int i = 0; i < 256; i++) {
            if (map[i] == 0) continue;
            if (buckets[map[i]] == null) buckets[map[i]] = new ArrayList<>();
            buckets[map[i]].add((char)i);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] != null) {
                for (char c : buckets[i]) {
                    for (int j = 0; j < i; j++) {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }
}
