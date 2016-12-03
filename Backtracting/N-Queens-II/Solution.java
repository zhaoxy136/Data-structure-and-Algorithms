public class Solution {
    private int count = 0;
    public int totalNQueens(int n) {
        helper(new ArrayList<Integer>(), n, 0);
        return count;
    }
    private void helper(List<Integer> list, int n, int row) {
        if (row == n) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!isAttack(list, row, i)) {
                list.add(i);
                helper(list, n, row+1);
                list.remove(list.size()-1);
            }
        }
    }
    private boolean isAttack(List<Integer> list, int row, int col){
        if (list.contains(col)) {
            return true;
        }
        for (int i = 0; i < list.size(); i++) {
            if (i + list.get(i) == row + col || i - list.get(i) == row - col) {
                return true;
            }
        }
        return false;
    }
}
