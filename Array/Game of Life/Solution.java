//Version 0:
public class Solution {
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int count = 0;
                for (int I = Math.max(0, i-1); I < Math.min(i+2, board.length); I++) {
                    for (int J = Math.max(0, j-1); J < Math.min(j+2, board[0].length); J++) {
                        count += board[I][J] & 1;
                    }
                }
                if (count == 3 || count == 4 && board[i][j] == 1) {
                    board[i][j] |= 2;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = board[i][j] >> 1;
            }
        }
    }
}

