class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        List<String>[] bucket = new List[words.length+1];
        for (String key : map.keySet()) {
            int count = map.get(key);
            if (bucket[count] == null) bucket[count] = new ArrayList<>();
            bucket[count].add(key);
        }
        List<String> res = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != null) {
                Collections.sort(bucket[i]);
                for (String s : bucket[i]) {
                    res.add(s);
                    if (res.size() == k) return res;
                }
            }
        }
        return res;
    }
}
