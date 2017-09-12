## 涉及题目
> [Path Sum](https://leetcode.com/problems/path-sum/description/)  
> [Path Sum II]()  
> [Path Sum III]()  
> [Path Sum IV]()  


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



































