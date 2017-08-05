public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] buckets = new List[nums.length+1];
        List<Integer> res = new ArrayList<>();
        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<Integer>();
            }
            buckets[freq].add(key);
        }
        for (int i = buckets.length-1; i>= 0 && k > 0; i--) {
            if (buckets[i] != null) {
                for (int n : buckets[i]) {
                    res.add(n);
                    if (--k <= 0) break;
                }
            }
        }
        return res;
    }
}
