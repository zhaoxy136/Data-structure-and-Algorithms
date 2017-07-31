//Version 0: use Trie and DFS
public class Solution {
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
    
    public class Trie {
        Node root;
        public Trie() {
            root = new Node(' ');
        }
        public void add(String str) {
            Node cur = root;
            for (char c : str.toCharArray()) {
                if (cur.child[c-'a'] == null) {
                    cur.child[c-'a'] = new Node(c);
                }
                cur = cur.child[c-'a'];
            }
            cur.isEnd = true;
        }
        public boolean startWith(String prefix) {
            Node cur = root;
            for (char c : prefix.toCharArray()) {
                if (cur.child[c-'a'] == null) return false;
                cur = cur.child[c-'a'];
            }
            return true;
        }
        public boolean exist(String str) {
            Node cur = root;
            for (char c : str.toCharArray()) {
                if (cur.child[c-'a'] == null) return false;
                cur = cur.child[c-'a'];
            }
            return cur.isEnd;
        }
    }
    
    int[] dir = new int[]{0, 1, 0, -1, 0};
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return res;
        Trie trie = new Trie();
        for (String word : words) {
            trie.add(word);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                helper(res, "", trie, board, new boolean[board.length][board[0].length], i, j);
            }
        }
        return res;
    }
    private void helper(List<String> res, String str, Trie trie, char[][] board, boolean[][] visited, int row, int col) {
        if (!trie.startWith(str) || row < 0 || col < 0 || row >= board.length || col >= board[0].length || visited[row][col]) {
            return;
        }
        str += board[row][col];
        if (trie.exist(str) && !res.contains(str)) {
            res.add(str);
        }
        visited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int x = row + dir[i];
            int y = col + dir[i+1];
            helper(res, str, trie, board, visited, x, y);
        }
        visited[row][col] = false;
    }
}

//Version 1: imporve the trie
public class Solution {
    public class TrieNode {
        TrieNode[] child = new TrieNode[26];
        String word;
    }
    public TrieNode construct(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode cur = root;
            for (char c : w.toCharArray()) {
                if (cur.child[c-'a'] == null) cur.child[c - 'a'] = new TrieNode();
                cur = cur.child[c - 'a'];
            }
            cur.word = w;
        }
        return root;
    }
    int[] dir = new int[]{0, 1, 0, -1, 0};
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = construct(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                helper(res, board, root, i, j);
            }
        }
        return res;
    }
    private void helper(List<String> res, char[][] board, TrieNode node, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return;
        char c = board[i][j];
        if (c == '#' || node.child[c-'a'] == null) return;
        node = node.child[c-'a'];
        if (node.word != null) {
            res.add(node.word);
            node.word = null;
        }
        board[i][j] = '#';
        for (int k = 0; k < 4; k++) {
            int row = i + dir[k];
            int col = j + dir[k+1];
            helper(res, board, node, row, col);
        }
        board[i][j] = c;
    }
}




