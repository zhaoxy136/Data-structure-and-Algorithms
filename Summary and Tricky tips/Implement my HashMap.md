## 基于对HashMap的理解，来实现简化版的HashMap

### 简介
HashMap/HashSet大家都不陌生，无论是在考试面试还是实际开发中都是最常见的数据结构之一。其插入和查找的高效性也是其最显著的特点。  
常常有人把HashMap比做抽屉，把相同的东西或者一类东西放在同一个抽屉里，这样无论在插入删除还是搜索中，只要马上找到对应的抽屉，就会极大的提高效率。  

### 思路
显然HashMap也是有更基本的数据结构来构成的。我们首先用一个数组来表示这些`抽屉`。而里面的内容我们用另一个内部类Node来表示。  
HashMap中的内容都是以(Key, Value) Pair的形式存在的(后文用Entry来代替)，value可以看为实质的信息，而key就相当于标签，用于寻在该放在哪个抽屉里。
既然我们的抽屉是一个数组，我们就需要一个方法使得Key和抽屉的index一一对应，这就是HashMap中最核心的内容 -- hash function。一个HashMap设计的好不好
很大程度上取决于hash function是否优秀，即能否尽可能将key平均的分配到不同的index中。

那么接下来的问题就是，处于同一个抽屉里的entry如何orgnaize呢？首先，最最理想的情况下是每个抽屉里都只有一个entry，那么我们找到了抽屉就相当于找到了结果。但
显然这不可能实现，尤其是当entry数量大于抽屉数量时必然会有冲突(我们称为collision)。那么最常见的应对collision的方法就是chaining，即用链式结构存放
同一个抽屉中的entries。这也是为什么我们将内部类命名为Node。  

以上就是我们简易版HashMap的设计思路。

### 代码实现

首先需要一个类的框架，包括内部类Node，HashMap的最大容量，当前entries数量，Node[]数组，以及构造函数。

    public static class Node<K, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;
        Node(int hash, K key, V val) {
            this.hash = hash;
            this.key = key;
            this.value = val;
        }
    }

    int capacity;
    int size;

    Node<K, V>[] table;

    /* when we try to find bucket with hash, usually we do index = hash % length
     * if capacity is 2 ^ k, instead we can use index = hash & (length - 1)
     * which is more efficient
     */
    public MyHashMap(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Invalid capacity" + initialCapacity);
        }
        this.size = 0;
        this.capacity = 1;
        while (capacity < initialCapacity) {
            capacity = capacity << 1;
        }
    }

对于HashMap的capacity，即抽屉的数量，本例中是将输入的capacity向上到2的次方。如输入17，实际设定的capacity是32。实际中也是如此操作的。  
原因正如comment所说，由于我们要根据hash的值对capacity取模来得到index，如果capacity是2^k的话取模操作会十分方便，大大提高了效率。  

接来下我们来实现最基本的一些方法：
+ int hash(Object key);  
+ void put(K key, V value);  
+ V get(K key);  
+ void remove(K key);  
+ boolean containsKey(K key);  
+ int size();  
+ boolean isEmpty();  

完整代码如下：


    public class MyHashMap<K extends Comparable, V extends Comparable> {

    public static class Node<K, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;
        Node(int hash, K key, V val) {
            this.hash = hash;
            this.key = key;
            this.value = val;
        }
    }
    static final int DEFAULT_SIZE = 1 << 4;//16

    private int capacity;
    private int size;

    private Node<K, V>[] table;

    //hash function
    static int hashFunc(Object key) {
        if (key == null) return 0;
        int h = key.hashCode();
        h ^= h >>> 16;
        return h;
    }

    /* when we try to find bucket with hash, usually we do index = hash % length
     * if capacity is 2 ^ k, instead we can use index = hash & (length - 1)
     * which is more efficient
     */
    public MyHashMap(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Invalid capacity" + initialCapacity);
        }
        this.size = 0;
        this.capacity = 1;
        while (capacity < initialCapacity && capacity > 0) {
            capacity = capacity << 1;
        }
        if (capacity < 0) capacity >>>= 1;
        this.table = new Node[capacity];
    }

    public MyHashMap() {
        this(DEFAULT_SIZE);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void put(K key, V value) {
        int hash = hashFunc(key);
        int index = hash & (capacity - 1);
        Node<K, V> entry = new Node<>(hash, key, value);
        if (table[index] == null) {
            table[index] = entry;
        } else {
            size += appendToList(table[index], entry);
        }
    }

    public V get(K key) {
        int hash = hashFunc(key);
        int index = hash & (capacity - 1);
        Node<K, V> head = table[index];
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        int hash = hashFunc(key);
        int index = hash & (capacity - 1);
        Node<K, V> head = table[index];
        while (head != null) {
            if (head.key.equals(key)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    public void remove(K key) {
        int hash = hashFunc(key);
        int index = hash & (capacity - 1);
        Node<K, V> dummy = new Node(0, null, null);
        dummy.next = table[index];
        Node<K, V> prev = dummy;
        Node<K, V> head = table[index];

        while (head != null) {
            if (head.key.equals(key)) {
                prev.next = head.next;
                size--;
                return;
            }
            prev = head;
            head = head.next;
        }
        table[index] = dummy.next;
    }

    private int appendToList(Node head, Node<K, V> node) {
        Node prev = head;
        while (head != null) {
            if (head.hash == node.hash && head.key.equals(node.key)) {
                head.value = node.value;
                return 0;
            }
            prev = head;
            head = head.next;
        }
        prev.next = node;
        return 1;
    }
    }









