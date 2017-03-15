//Version 0: DFS
public class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        helper(board, click[0], click[1]);
        return board;
    }
    
    private void helper(char[][] board, int row, int col) {
    if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] != 'E') return;
    if (board[row][col] != 'M') {
        int count = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0 || row+i < 0 || col+j < 0 || row+i >= board.length || col+j >= board[0].length) continue;
                if (board[row+i][col+j] == 'M') count++;
            }
        }
        if (count > 0) board[row][col] = (char)(count + '0');
        else {
            board[row][col] = 'B';
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (i == 0 && j == 0) continue;
                    helper(board, row+i, col+j);
                }
            }
        }
    }
    }
}
