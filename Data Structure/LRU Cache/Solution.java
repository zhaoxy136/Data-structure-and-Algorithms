//Version 0: HashMap + Doubly LinkedList
public class LRUCache {
    public class ListNode {
        ListNode prev;
        ListNode next;
        int key;
        int val;
        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }
    private int capacity;
    private Map<Integer, ListNode> map;
    private ListNode head = new ListNode(0, 0);
    private ListNode tail = new ListNode(0, 0);
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        ListNode node = map.get(key);
        updateList(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        
        if (map.containsKey(key)) {
            updateList(map.get(key));
            map.get(key).val = value;
        } else {
            ListNode node = new ListNode(key, value);
            map.put(key, node);
            node.prev = tail.prev;
            tail.prev.next = node;
            node.next = tail;
            tail.prev = node;
            if (map.size() > capacity) {
                ListNode tmp = head.next;
                head.next = head.next.next;
                head.next.prev = head;
                map.remove(tmp.key);
            }
        }
    }
    
    public void updateList(ListNode node) {
        ListNode tmp = node.prev;
        
        tmp.next = node.next;
        node.next.prev = tmp;
        
        node.prev = tail.prev;
        tail.prev.next = node;
        node.next = tail;
        tail.prev = node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
