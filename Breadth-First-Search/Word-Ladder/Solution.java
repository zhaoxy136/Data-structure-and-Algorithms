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

//Version 1: from @nine charpter
public class Solution {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        set.add(beginWord);
        queue.offer(beginWord);
        wordList.add(endWord);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String start = queue.poll();
                for (String str : getNextWords(wordList, start)) {
                    if (str.equals(endWord)) return level+1;
                    if (!set.contains(str)) {
                        queue.offer(str);
                        set.add(str);
                    }
                }
            }
            level++;
        }
        return 0;
    }
    
    private String replace(String word, int index, char c) {
        char[] chars = word.toCharArray();
        chars[index] = c;
        return new String(chars);
    }
    private Set<String> getNextWords(Set<String> dict, String word) {
        Set<String> nextWords = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (word.charAt(i) == c) continue;
                String newWord = replace(word, i, c);
                if (dict.contains(newWord)) {
                    nextWords.add(newWord);
                }
            }
        }
        return nextWords;
    }
}

//Version 2: Two End BFS, much more faster 
public class Solution {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> visited = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        visited.add(beginWord);
        visited.add(endWord);
        int len = 1;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> tmp = beginSet;
                beginSet = endSet;
                endSet = tmp;
            }
            Set<String> next = new HashSet<>();
            for (String str : beginSet) {
                for (int i = 0; i < str.length(); i++) {
                    char[] chars = str.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String word = new String(chars);
                        if (endSet.contains(word)) return len+1;
                        if (!visited.contains(word) && wordList.contains(word)) {
                            next.add(word);
                            visited.add(word);
                        }
                    }
                }
            }
            beginSet = next;
            len++;
        }
        return 0;
    }
}
