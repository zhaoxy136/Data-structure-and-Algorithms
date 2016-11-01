public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        findPath(results,solution,root,sum);
        return results;
    }
    public void findPath(List<List<Integer>> results,
            List<Integer> solution, TreeNode root, int sum){
        if(root == null){
            return;
        }
        solution.add(root.val);

        if(root.left == null && root.right == null){
            if(root.val == sum){
                results.add(new ArrayList (solution));
            }
            solution.remove(solution.size()-1);
            return;
        }
        //divide and conquer
        findPath(results, solution, root.left, sum - root.val);
        findPath(results, solution, root.right, sum - root.val);
        solution.remove(solution.size()-1);
    }
}
