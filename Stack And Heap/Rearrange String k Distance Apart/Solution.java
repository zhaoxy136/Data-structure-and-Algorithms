//Version 0:
//@copyright:https://discuss.leetcode.com/topic/48109/java-7-version-of-priorityqueue-o-nlogn-with-comments-and-explanations
public class Solution {
    public String rearrangeString(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> freq = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> a, Map.Entry<Character, Integer> b) {
                //Note: heap sort is not stable
                return b.getValue() != a.getValue() ? b.getValue() - a.getValue() : a.getKey() - b.getKey();
            }
        });
        freq.addAll(map.entrySet());
        StringBuilder sb = new StringBuilder();
        Queue<Map.Entry<Character, Integer>> wait = new LinkedList<>();
        while (!freq.isEmpty()) {
            Map.Entry<Character, Integer> temp = freq.poll();
            sb.append(temp.getKey());
            temp.setValue(temp.getValue()-1);
            wait.add(temp);
            if (wait.size() < k) continue;
            Map.Entry<Character, Integer> e = wait.poll();
            if (e.getValue() > 0) freq.add(e);
        }
        return sb.length() == s.length() ? sb.toString() : "";
    }
}
