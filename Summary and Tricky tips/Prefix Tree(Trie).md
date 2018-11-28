## 如何运用字典树／前缀树
### 涉及题目
> [Implement Trie](https://leetcode.com/problems/implement-trie-prefix-tree/description/)  
> [Prefix and Suffix Search](https://leetcode.com/problems/prefix-and-suffix-search/description/)  
> [Design Search Autocomplete System](https://leetcode.com/problems/design-search-autocomplete-system/description/)

### Clarification
面试中沟通十分重要，不要拿到题就埋头开始做，一定要先和面试官确定题意，并且做一些clarification，甚至Assumption。
对于Trie的问题，通常需要确认的有以下几点：
+ what is the scale of the dictionary? What is the average lengh of the words?
+ how many possible characters are included, [a-z]? or with some other characters?
+ duplicate words are possible, how would that influence the system?
+ how frequently does the system insert, and how frequent does it read/query?
第一点十分重要，他甚至直接决定了Trie是否是一个很好的符合题意的设计
第二点也是必问的，这才才知道TrieNode有多少个子分支
第四点是对于如何具体实现十分重要的点，这样我们才能决定我们要更加注重那一种操作的复杂度


### Implement Trie
理解Trie入门第一题，帮助我们熟悉Trie的基本操作，在官方解答部分也列举出来Trie在实际中应用的例子。
简单来说Trie可以视为一个26-ary的Tree（如果只支持a-z），基本的思想和二叉树类似，其操作有insert 一个单词，search一个单词或者前缀。
TrieNode类最简单的表示为：
```
 class TrieNode {
        char val;
        TrieNode[] children;
        boolean isWord;
        public TrieNode(char val) {
            this.val = val;
            this.children = new TrieNode[26];
            this.isWord = false;
        }
}
```
相对应的插入和搜索也十分简单
```
    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.children[c-'a'] == null) {
                cur.children[c-'a'] = new TrieNode(c);
            }
            cur = cur.children[c-'a'];
        }
        cur.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.children[c-'a'] == null) {
                return false;
            }
            cur = cur.children[c-'a'];
        }
        return cur.isWord;
    }
```

此外表示TrieNode还可以使用另外一种写法：
```
class TrieNode {
    TrieNode[] child = new TrieNode[26];
    String word;
    public TrieNode() {
        
    }
}
```
那么对应的insert和search操作为
```
    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.children[c-'a'] == null) {
                cur.children[c-'a'] = new TrieNode();
            }
            cur = cur.children[c-'a'];
        }
        cur.word = word;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.children[c-'a'] == null) {
                return false;
            }
            cur = cur.children[c-'a'];
        }
        return cur.word.equals(word);
    }
```
### Prefix and Suffix Search
此题是一个Trie的延伸，主要考察如何灵活的构建Trie，虽然Trie只能对前缀进行搜索，但只要我们根据题目的需要合理地构建，也可以达到同时搜索prefix和suffix的效果。
#### 思路一：拿到题一般会先想到的方法是构建两个Trie，一个用于前缀，一个用于后缀。问题的关键是如何保证搜索到的prefix和suffix来自同一个单词。这就要求TrieNode保存所有单词的index，即weight。
```
class WordFilter {
    TrieNode trie1, trie2;
    public WordFilter(String[] words) {
        trie1 = new TrieNode();
        trie2 = new TrieNode();
        int wt = 0;
        for (String word: words) {
            char[] ca = word.toCharArray();

            TrieNode cur = trie1;
            cur.weight.add(wt);
            for (char letter: ca) {
                if (cur.children[letter - 'a'] == null)
                    cur.children[letter - 'a'] = new TrieNode();
                cur = cur.children[letter - 'a'];
                cur.weight.add(wt);
            }

            cur = trie2;
            cur.weight.add(wt);
            for (int j = ca.length - 1; j >= 0; --j) {
                char letter = ca[j];
                if (cur.children[letter - 'a'] == null)
                    cur.children[letter - 'a'] = new TrieNode();
                cur = cur.children[letter - 'a'];
                cur.weight.add(wt);
            }
            wt++;
        }
    }

    public int f(String prefix, String suffix) {
        TrieNode cur1 = trie1, cur2 = trie2;
        for (char letter: prefix.toCharArray()) {
            if (cur1.children[letter - 'a'] == null) return -1;
            cur1 = cur1.children[letter - 'a'];
        }
        char[] ca = suffix.toCharArray();
        for (int j = ca.length - 1; j >= 0; --j) {
            char letter = ca[j];
            if (cur2.children[letter - 'a'] == null) return -1;
            cur2 = cur2.children[letter - 'a'];
        }

        int ans = -1;
        for (int w1: cur1.weight)
            if (w1 > ans && cur2.weight.contains(w1))
                ans = w1;

        return ans;
    }
}

class TrieNode {
    TrieNode[] children;
    Set<Integer> weight;
    public TrieNode() {
        children = new TrieNode[26];
        weight = new HashSet();
    }
}
```

#### 思路二：那么能否只用一个Trie就达到同样的效果呢？我们可以在对单词进行储存时进行如下变形：即当需要存入apple时，我们分别存入#apple, e#apple, le#apple, ple#apple, pple#apple, apple#apple。即后缀#前缀的格式。
如此以来，当进行query prefix: ap, suffix: le时，我们只需在Trie中搜索le#ap即可。而且一个TrieNode只需保存最大的weight，因为前缀和后缀是对应储存的。
另外一点不同的是，我们引入了#，那么Trie中包含的字符就不只是a-z了。我们同时也可以用到一个小优化，即用HashMap<Character, TrieNode> 来替代TrieNode array。某种程度上可以起到节省空间的作用。
```
class WordFilter {
    class TrieNode {
        int weight;
        Map<Character, TrieNode> children;
        public TrieNode(int weight) {
            this.weight = weight;
            this.children = new HashMap<>();
        }
    }
    
    TrieNode root;
    public WordFilter(String[] words) {
        root = new TrieNode(-1);
        for (int w = words.length-1; w >= 0; --w) {
            int len = words[w].length();
            for (int j = len; j >= 0; --j) {
                addWord(words[w].substring(j) + "#" + words[w], w);
            }
        }
    }
    
    public int f(String prefix, String suffix) {
        String pattern = suffix + "#" + prefix;
        TrieNode cur = root;
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (!cur.children.containsKey(c)) return -1;
            cur = cur.children.get(c);
        }
        return cur.weight;
    }
    
    private void addWord(String word, int weight) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new TrieNode(weight));
            }
            cur = cur.children.get(c);
        }
    }  
}
```

### Design Search Autocomplete System
面试高频题，题目有一些设计的思想在其中，但并没有上一道题那么tricky。
对于这类问题，最最重要的是要和面试官沟通好题意，然后分析方案的可行性和优缺点。
最后再准确并熟练的实现出来。
本题的唯一不同的点在于当搜索到当前prefix的时候，需要对其所有的单词进行排序，如果每次都做dfs那么time complexity就会很大。
对于我们可以采取空间换时间的策略，对于每个Node都保存一个hashmap<String, Integer>来存储以其为前缀的所有单词出现的次数。
```
class TrieNode {
    Map<String, Integer> map;
    TrieNode[] children;
    public TrieNode() {
        this.map = new HashMap<>();
        this.children = new TrieNode[27];
    }
}
```
insert() 和 get()都是基本写法，唯一不同的是，我们在query的时候需要对hashmap中的所有string，count pair进行排序，然后输出前三即可。
```
public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        cur = new StringBuilder();
        k = 3;
        for (int i = 0; i < sentences.length; i++) {
            addSentence(sentences[i], times[i]);
        }
    }
    
    private void addSentence(String sentence, int time) {
        TrieNode node = root;
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            int index = c == ' ' ? 26 : c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
            node.map.put(sentence, node.map.getOrDefault(sentence, 0) + time);
        }
    }
    
    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c != '#') {
            cur.append(c);
            TrieNode node = root;
            for (int i = 0; i < cur.length(); i++) {
                char ch = cur.charAt(i);
                int index = ch == ' ' ? 26 : ch - 'a';
                if (node.children[index] == null) return res;
                node = node.children[index];
            }
            Map<String, Integer> myMap = node.map;
            PriorityQueue<String> queue = new PriorityQueue<>((a, b) -> (myMap.get(a) == myMap.get(b) ? a.compareTo(b) : myMap.get(b) - myMap.get(a)));
            for (String s : myMap.keySet()) {
                queue.add(s);
            }
            int size = k;
            while (!queue.isEmpty() && size-- > 0) {
                res.add(queue.poll());
            }
        } else {
            addSentence(cur.toString(), 1);
            cur = new StringBuilder();
        }
        return res;
    }
```

### 小结
+ TrieNode class 的表示方法有很多种，具体使用那一种要根据不同的题目做出分析，但无论何种表示，其内在的思想都是一样的
+ Trie树占用内存较大，例如：处理最大长度为20、全部为小写字母的一组字符串，则可能需要20^26个节点来保存数据。而这样的树实际上稀疏的十分厉害，可以采用需要多少子节点则添加多少子节点来解决（不要类似以上的示例，每个节点初始化时就申请一个长度为26的数组）。所以Trie相对适合用于**单词个数巨大，但平均单词长度很小的情况**。
+ 有一点要注意的是，如果使用HashMap来代替array的话，那么每一层需要遍历的时候就不会是按照字典序输出了。


### 其他相关题目
> [Implement Magic Dictionary](https://leetcode.com/problems/implement-magic-dictionary/description/)  
> 
