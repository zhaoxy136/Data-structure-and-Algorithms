//Version 0: 23ms
public class Solution {
    int[] dir = new int[]{1, 0, -1, 0, 1};
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0) && helper(board, word, new boolean[board.length][board[0].length], i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean helper(char[][] board, String word, boolean[][] visited, int row, int col, int start) {
        if (start >= word.length()) return true;
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length ||
            visited[row][col] || board[row][col] != word.charAt(start)) return false;
        visited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            if (helper(board, word, visited, row + dir[i], col + dir[i+1], start+1)) return true;
        }
        visited[row][col] = false;
        return false;
    }
}

//Version 1: use '#' mark instead of visited matrix, 9ms
public class Solution {
    int[] dir = new int[]{1, 0, -1, 0, 1};
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0) && helper(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean helper(char[][] board, String word, int row, int col, int start) {
        if (start >= word.length()) return true;
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) return false;
        if (board[row][col] == '#' || board[row][col] != word.charAt(start)) return false;
        char c = board[row][col];
        board[row][col] = '#';
        for (int i = 0; i < 4; i++) {
            if (helper(board, word, row + dir[i], col + dir[i+1], start+1)) return true;
        }
        board[row][col] = c;
        return false;
    }
}
