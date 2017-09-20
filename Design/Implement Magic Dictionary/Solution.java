//Version 0: Using hashset, space consuming
class MagicDictionary {
    Set<String> set;
    /** Initialize your data structure here. */
    public MagicDictionary() {
        set = new HashSet<>();
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String word : dict) {
            helper(set, word);
        }
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        return set.contains(word);
    }
    
    public void helper(Set<String> set, String word) {
        char[] w = word.toCharArray();
        for (int i = 0; i < w.length; i++) {
            char c = w[i];
            for (char j = 'a'; j <= 'z'; j++) {
                if (j == c) continue;
                w[i] = j;
                set.add(new String(w));
            }
            w[i] = c;
        }
    }
}

//Version 1: using hashmap, using much less space, better time complexity when build dict
class MagicDictionary {
    Map<String, List<int[]>> map;
    /** Initialize your data structure here. */
    public MagicDictionary() {
        map = new HashMap<>();
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String word : dict) {
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + word.substring(i+1);
                int[] p = new int[]{i, word.charAt(i)};
                List<int[]> list = map.getOrDefault(key, new ArrayList<>());
                list.add(p);
                map.put(key, list);
            }
        }
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        for (int i = 0; i < word.length(); i++) {
            String w = word.substring(0, i) + word.substring(i+1);
            if (map.containsKey(w)) {
                for (int[] p : map.get(w)) {
                    if (p[0] == i && p[1] != word.charAt(i)) return true;
                }
            }
        }
        return false;
    }
}

//Version 2: using trie
class MagicDictionary {
    class TrieNode {
        char c;
        boolean isEnd;
        TrieNode[] child;
        public TrieNode(char ch) {
            this.c = ch;
            this.isEnd = false;
            this.child = new TrieNode[26];
        }
    }
    TrieNode root;
    /** Initialize your data structure here. */
    public MagicDictionary() {
        root = new TrieNode(' ');
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        TrieNode cur;
        for (String word : dict) {
            cur = root;
            for (char c : word.toCharArray()) {
                if (cur.child[c - 'a'] == null) {
                    cur.child[c - 'a'] = new TrieNode(c);
                }
                cur = cur.child[c - 'a'];
            }
            cur.isEnd = true;
        }
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        return helper(word, root, 0, 0);
    }
    
    public boolean helper(String word, TrieNode root, int index, int count) {
        if (index == word.length()) {
            return root.isEnd && count == 1;
        }
        TrieNode cur = root;
        char ch = word.charAt(index);
        for (int i = 0; i < 26; i++) {
            if (cur.child[i] != null) {
                int nextCount = i == ch - 'a' ? count : count + 1;
                if (nextCount > 1) continue;
                if (helper(word, cur.child[i], index+1, nextCount)) return true;
            }
        }
        return false;
    }
}

