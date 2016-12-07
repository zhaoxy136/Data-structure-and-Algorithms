//Version 0: BFS, using hashmap
public class Solution {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        map.put(beginWord, 1);
        queue.offer(beginWord);
        wordList.add(endWord);
        while (!queue.isEmpty()) {
            String start = queue.poll();
            int level = map.get(start);
            //System.out.println(level);
            for (String str : getNextWords(wordList, start)) {
                if (!map.containsKey(str)) {
                    queue.offer(str);
                    map.put(str, level+1);
                }
            }
            if (map.containsKey(endWord)) {
                return map.get(endWord);
            }
        }
        return 0;
    }
    
    private Set<String> getNextWords(Set<String> dict, String word) {
        Set<String> nextWords = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (word.charAt(i) == c) continue;
                char[] chars = word.toCharArray();
                chars[i] = c;
                String newWord = new String(chars);
                if (dict.contains(newWord)) {
                    nextWords.add(newWord);
                }
            }
        }
        return nextWords;
    }
}

//Version 1: 
