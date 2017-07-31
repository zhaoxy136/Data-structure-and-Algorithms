public class WordDictionary {
    public class TrieNode {
        TrieNode[] child = new TrieNode[26];
        String word;
    }
    TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (cur.child[i] == null) cur.child[i] = new TrieNode();
            cur = cur.child[i];
        }
        cur.word = word;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return helper(word, root, 0);
    }
    private boolean helper(String word, TrieNode node, int start) {
        for (int i = start; i < word.length(); i++) {
            if (word.charAt(i) != '.') {
                if (node.child[word.charAt(i)-'a'] == null) return false;
                node = node.child[word.charAt(i)-'a'];
            } else {
                for (int j = 0; j < 26; j++) {
                    if (node.child[j] != null && helper(word, node.child[j], i+1)) return true;
                }
                return false;
            }
        }
        return node.word != null;
    }
}
