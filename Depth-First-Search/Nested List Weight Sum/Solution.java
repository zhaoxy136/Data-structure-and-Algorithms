/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        return helper(nestedList,1);
    }
    public int helper(List<NestedInteger> nestedList, int depth) {
        int res = 0;
        for (NestedInteger nl : nestedList) {
            if (nl.isInteger()) {
                res += nl.getInteger() * depth;
            } else {
                res += helper(nl.getList(), depth+1);
            }
        }
        return res;
    }
}
