public class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        helper(res, list, n, 0);
        return res;
    }
    
    private void helper(List<List<String>> res, List<Integer> list, int n, int row){
        if(row == n){
            List<String> temp = new ArrayList<>();
            for(int i = 0; i < n; i++){
                String str = "";
                for(int j = 0; j < n; j++){
                    if(list.get(i) == j){
                        str += "Q";
                    }else{
                        str += ".";
                    }
                }
                temp.add(str);
            }
            res.add(temp);
            return;
        }
        for(int col = 0; col < n; col++){
            if(!isAttack(list, row, col)){
                list.add(col);
                helper(res, list, n, row+1);
                list.remove(list.size()-1);
            }
        }
    }
    
    private boolean isAttack(List<Integer> list, int row, int col){
        if(list.contains(col)){
            return true;
        }
        for(int i = 0; i < list.size(); i++){
            if((col + row == list.get(i) + i) || col - row == list.get(i) - i){
                return true;
            }
        }
        return false;
    }
}
