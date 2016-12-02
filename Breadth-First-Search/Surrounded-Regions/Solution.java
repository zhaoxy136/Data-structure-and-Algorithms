//Version 0: DFS, stackoverflow
public class Solution {
    public void solve(char[][] board) {
        if (board.length < 3 || board[0].length < 3) return;
        //boolean[][] escaped = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            dfs(board, i, 0);
            dfs(board, i, board[0].length-1);
        }
        for (int i = 1; i < board[0].length-1; i++) {
            dfs(board, 0, i);
            dfs(board, board.length-1, i);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'E') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    private void dfs(char[][] board, int row, int col) {
        if (row < 0 || row == board.length || col < 0 || col == board[0].length || board[row][col] != 'O') {
            return;
        }
        board[row][col] = 'E';
        //escaped[row][col] = true;
        dfs(board, row+1, col);
        dfs(board, row-1, col);
        dfs(board, row, col+1);
        dfs(board, row, col-1);
    }
}

//Version 1: simple modified DFS
public class Solution {
    public void solve(char[][] board) {
        if (board.length < 3 || board[0].length < 3) return;
        for (int i = 0; i < board.length; i++) {
            dfs(board, i, 0);
            dfs(board, i, board[0].length-1);
        }
        for (int i = 1; i < board[0].length-1; i++) {
            dfs(board, 0, i);
            dfs(board, board.length-1, i);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'E') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    private void dfs(char[][] board, int row, int col) {
        if (board[row][col] != 'O') return;
        board[row][col] = 'E';
        if (row > 1) {
            dfs(board, row-1, col);
        }
        if (row < board.length-2) {
            dfs(board, row+1, col);
        }
        if (col > 1) {
            dfs(board, row, col-1);
        }
        if (col < board[0].length-2) {
            dfs(board, row, col+1);
        }
    }
}

//Version 2: BFS, using queue
public class Solution {
    public void solve(char[][] board) {
        if (board.length < 3 || board[0].length < 3) return;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') {
                queue.offer(new int[]{i, 0});
            }
            if (board[i][board[0].length-1] == 'O') {
                queue.offer(new int[]{i, board[0].length-1});
            }
        }
        for (int i = 1; i < board[0].length-1; i++) {
            if (board[0][i] == 'O') {
                queue.offer(new int[]{0, i});
            }
            if (board[board.length-1][i] == 'O') {
                queue.offer(new int[]{board.length-1, i});
            }
        }
        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int row = tmp[0];
            int col = tmp[1];
            board[row][col] = 'E';
            if (row > 0 && board[row-1][col] == 'O') {
                queue.offer(new int[]{row-1, col});
            }
            if (col > 0 && board[row][col-1] == 'O') {
                queue.offer(new int[]{row, col-1});
            }
            if (row < board.length-1 && board[row+1][col] == 'O') {
                queue.offer(new int[]{row+1, col});
            }
            if (col < board[0].length-1 && board[row][col+1] == 'O') {
                queue.offer(new int[]{row, col+1});
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'E') {
                    board[i][j] = 'O';
                }
            }
        }
    }
}
