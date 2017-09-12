## 涉及题目
> [Path Sum](https://leetcode.com/problems/path-sum/description/)  
> [Path Sum II]()  
> [Path Sum III]() 


## 思路分析

### Path Sum
题目要求很简单，判断一个树中是否包含从root到leaf的路径。可以用递归来解决。但一定要注意的是：递归的最底层应该是判定leaf
，即(root.left == null && root.right == null)，否则会出现错误的情况。

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        sum -= root.val;
        if (root.left == null && root.right == null) return sum == 0;
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }
    
    
### Path Sum II
在上一题的基础上要求返回的是**所有满足条件**的方案路径，因为要求返回具体方案，我们采用backtracking的解法。需要特别注意的是无论成功与否都要
在返回上一层之前将最后一个节点从list中去除

    class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), root, sum);
        return res;
    }
    
    public void helper(List<List<Integer>> res, List<Integer> list, TreeNode root, int sum) {
        if (root == null) return;
        list.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            res.add(new ArrayList<>(list));
        } else {
            helper(res, list, root.left, sum - root.val);
            helper(res, list, root.right, sum - root.val);
        }
        list.remove(list.size() - 1);
    }
    }

### Path Sum III
本题又是对于第二题的延伸，不再要求路径是root to leaf了，而是node to node，只要向下的路径即可。且只需要返回路径的数量。  
虽然也可以用recursive来解决，但稍微tricky，面试时容易一时想不出具体思路。  
这里首先给出的解法是**前缀和**的思路，我们从上向下遍历，将树节点value的和储存起来，每遍历一个点就像上找是否已有前缀和为preSum-sum的路径。如果存在
则记录满足条件的路径数量（因为可能有0值存在，所以路径数量可以大于1）。整体思路可以归结为DFS+HashMap。  
需要注意的两点：一是每一层返回之前要将该层的presum从map中去除，因为题目要求的路径是单向向下的。  
第二点是在刚开始的时候需要将（0，1）放入map，否则会漏掉从根节点出发的路径数量。

    class Solution {
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return helper(map, root, sum, 0);
    }
    public int helper(Map<Integer, Integer> map, TreeNode root, int sum, int preSum) {
        if (root == null) return 0;
        preSum += root.val;
        int count = map.getOrDefault(preSum - sum, 0);
        map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        count += helper(map, root.left, sum, preSum);
        count += helper(map, root.right, sum, preSum);
        map.put(preSum, map.get(preSum) - 1);
        return count;
    }
    }

在此也放出recursive的解法，仔细分析可知，这种解法存在大量重复计算，所以复杂度高于第一种方法

    class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return helper(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    //calculate num of paths start from root
    public int helper(TreeNode root, int sum) {
        if (root == null) return 0;
        sum -= root.val;
        int count = sum == 0 ? 1 : 0;
        count += helper(root.left, sum);
        count += helper(root.right, sum);
        return count;
    }
    }

