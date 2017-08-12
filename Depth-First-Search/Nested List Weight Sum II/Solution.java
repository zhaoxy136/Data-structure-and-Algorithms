//Version 0:
//@copyright: https://discuss.leetcode.com/topic/49041/no-depth-variable-no-multiplication
public class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int weighted = 0;
        int unweighted = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> next = new ArrayList<>();
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger()) {
                    unweighted += ni.getInteger();
                } else {
                    next.addAll(ni.getList());
                }
            }
            weighted += unweighted;
            nestedList = next;
        }
        return weighted;
    }
}

//Version 1: using Queue
public class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>();
        for (NestedInteger list : nestedList) {
            queue.offer(list);
        }
        int total = 0;
        int prev = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                NestedInteger tmp = queue.poll();
                if (tmp.isInteger()) {
                    prev += tmp.getInteger();
                } else {
                    for (NestedInteger list : tmp.getList()) {
                        queue.offer(list);
                    }
                }
            }
            total += prev;
        }
        return total;
    }
}
