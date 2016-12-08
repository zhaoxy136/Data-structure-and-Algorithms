//Version 0: BFS & DFS
public class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        Map<String, Integer> distance = new HashMap<>();
        Map<String, List<String>> map = new HashMap<>();
        wordList.add(beginWord);
        wordList.add(endWord);
        bfs(endWord, beginWord, wordList, distance, map);
        List<List<String>> res = new ArrayList<>();
        if (!distance.containsKey(beginWord)) return res;
        dfs(res, new ArrayList<>(), beginWord, endWord, distance, map);
        return res;
    }
    private void dfs(List<List<String>> res, List<String> list, String start, String end,
                Map<String, Integer> distance, Map<String, List<String>> map) {
        list.add(start);
        if (start.equals(end)) {
            res.add(new ArrayList<String>(list));
        } else {
            for (String str : map.get(start)) {
                if (distance.containsKey(str) && distance.get(start) == distance.get(str) + 1)
                    dfs(res, list, str, end, distance, map);
            }
        }
        list.remove(list.size()-1);
    }
    
    private void bfs(String start, String end, Set<String> wordList, 
                Map<String, Integer> distance, Map<String, List<String>> map) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distance.put(start, 1);
        for (String s : wordList) {
            map.put(s, new ArrayList<String>());
        }
        while (!queue.isEmpty()) {
            String str = queue.poll();
            int level = distance.get(str);
            for (int i = 0; i < str.length(); i++) {
                char[] chars = str.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    if (str.charAt(i) == c) continue;
                    chars[i] = c;
                    String next = new String(chars);
                    if (wordList.contains(next)) {
                        map.get(str).add(next);
                        if (!distance.containsKey(next)) {
                            distance.put(next, level+1);
                            queue.offer(next);
                        }
                    }
                }
            }
        }
    }
}

//Version 1: two end @copyright https://discuss.leetcode.com/topic/44621/25ms-beats-100-java-solution
