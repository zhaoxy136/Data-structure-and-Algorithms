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

//Version 1:





