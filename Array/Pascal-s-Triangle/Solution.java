public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0){
            return res;
        }
        List<Integer> first = new ArrayList<>(1);
        first.add(1);
        res.add(first);
        
        for (int i = 1; i < numRows; i++){
            List<Integer> row = new ArrayList<>(i+1);
            List<Integer> prev = res.get(i-1);
            row.add(1);
            for (int j = 1; j < i ; j++){
                int tmp = prev.get(j-1) + prev.get(j);
                row.add(j,tmp);
            }
            row.add(1);
            res.add(row);
        }
        return res;
    }
}
