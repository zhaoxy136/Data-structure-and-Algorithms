//Version 0:original
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        Map<Character, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    List<int[]> list = map.getOrDefault(board[i][j], new ArrayList<int[]>());
                    if (!isValid(list, i, j)) return false;
                    list.add(new int[]{i, j});
                    map.put(board[i][j], list);
                }
            }
        }
        return true;
    }
    
    public boolean isValid(List<int[]> list, int row, int col) {
        int cr = (row / 3) * 3 + 1;
        int cc = (col / 3) * 3 + 1;
        for (int[] pair : list) {
            if (pair[0] == row) return false;
            if (pair[1] == col) return false;
            if (Math.abs(pair[0] - cr) < 2 && Math.abs(pair[1] - cc) < 2) return false;
        }
        return true;
    }
}

//Version 1:
//@copyright: https://discuss.leetcode.com/topic/8241/my-short-solution-by-c-o-n2
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][][] used = new boolean[9][9][3];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0' - 1;
                    int k = (i / 3) * 3 + (j / 3);
                    if (used[i][num][0] || used[j][num][1] || used[k][num][2]) return false;
                    used[i][num][0] = true;
                    used[j][num][1] = true;
                    used[k][num][2] = true;
                }
            }
        }
        return true;
    }
}
