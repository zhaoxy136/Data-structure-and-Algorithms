public class Trie {
    public class Node {
        char ch;
        boolean isEnd;
        Node[] child;
        public Node(char ch) {
            this.ch = ch;
            this.isEnd = false;
            this.child = new Node[26]; 
        }
    }
    Node root;
    /** Initialize your data structure here. */
    public Trie() {
        this.root = new Node(' ');
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (cur.child[c-'a'] == null) {
                cur.child[c-'a'] = new Node(c);
            }
            cur = cur.child[c-'a'];
        }
        cur.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (cur.child[c-'a'] == null) return false;
            cur = cur.child[c-'a'];
        }
        return cur.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node cur = root;
        for (char c : prefix.toCharArray()) {
            if (cur.child[c-'a'] == null) return false;
            cur = cur.child[c-'a'];
        }
        return true;
    }
}
