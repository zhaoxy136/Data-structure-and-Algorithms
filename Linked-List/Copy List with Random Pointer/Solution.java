//Version 0:
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        RandomListNode node = head;
        while (node != null) {
            RandomListNode tmp = node.next;
            node.next = new RandomListNode(node.label);
            node.next.next = tmp;
            node = tmp;
        }
        node = head;
        while (node != null) {
            node.next.random = node.random == null ? null : node.random.next;
            node = node.next.next;
        }
        node = head;
        RandomListNode newHead = head.next;
        while (node != null) {
            RandomListNode tmp = node.next.next;
            node.next.next = tmp == null ? null : tmp.next;
            node.next = tmp;
            node = tmp;
        }
        return newHead;
    }
}
