//Version 0:
public class RandomizedCollection {
    public class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
    Map<Integer, ListNode> map;
    List<Integer> list;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            map.put(val, new ListNode(-1));
            map.get(val).next = new ListNode(list.size());
            list.add(val);
            return true;
        }
        ListNode start = map.get(val);
        while (start.next != null) {
            start = start.next;
        }
        start.next = new ListNode(list.size());
        list.add(val);
        return false;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val) || map.get(val).next == null) {
            return false;
        }
        ListNode head = map.get(val);
        int index = head.next.val;
        int last = list.get(list.size() - 1);
        list.set(index, last);
        ListNode start = map.get(last);
        while (start.val != list.size() - 1) {
            start = start.next;
        }
        start.val = index;
        head.next = head.next.next;
        list.remove(list.size() - 1);
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
}

//Version 1:
public class RandomizedCollection {
    Map<Integer, Set<Integer>> map;
    List<Integer> list;
    Random rand;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        map = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contain = map.containsKey(val);
        Set<Integer> pos = map.getOrDefault(val, new HashSet<>());
        pos.add(list.size());
        map.put(val, pos);
        list.add(val);
        return !contain;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        if (!map.get(val).contains(list.size() - 1)) {
            int index = map.get(val).iterator().next();
            int last = list.get(list.size() - 1);
            map.get(val).remove(index);
            map.get(val).add(list.size() - 1);
            map.get(last).remove(list.size() - 1);
            map.get(last).add(index);
            list.set(index, last);
        }
        map.get(val).remove(list.size() - 1);
        if (map.get(val).isEmpty()) map.remove(val);
        list.remove(list.size() - 1);
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
