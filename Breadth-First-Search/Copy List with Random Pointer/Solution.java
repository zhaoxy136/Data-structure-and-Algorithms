
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
//Version 0: using hashmap O(n) space
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        Map<RandomListNode,RandomListNode> map = new HashMap<>();
        RandomListNode node = head;
        //clone nodes
        while (node != null) {
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }
        //clone pointers
        for (RandomListNode tmp : map.keySet()) {
            map.get(tmp).next = map.get(tmp.next);
            map.get(tmp).random = map.get(tmp.random);
        }
        return map.get(head);
    }
}

//Version 1:
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        //if (head == null) return null;
        RandomListNode iter = head;
        RandomListNode next = null;
        //step 1: clone nodes
        while (iter != null) {
            next = iter.next;
            iter.next = new RandomListNode(iter.label);
            iter.next.next = next;
            iter = next;
        }
        //step 2: clone pointers
        iter = head;
        while (iter != null) {
            if (iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }
        //step 3: restore original list
        //NOTICE: AVOID ITERATE NULL.NEXT!!!
        RandomListNode res = new RandomListNode(0);
        RandomListNode copy = res;
        iter = head;
        while (iter != null) {
            next = iter.next.next;
            copy.next = iter.next;
            iter.next = next;
            iter = next;
            copy = copy.next;
        }
        return res.next;
    }
}
