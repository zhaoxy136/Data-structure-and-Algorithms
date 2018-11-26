## 如何运用字典树／前缀树
### 涉及题目
> [Implement Trie](https://leetcode.com/problems/implement-trie-prefix-tree/description/)  
> [Prefix and Suffix Search](https://leetcode.com/problems/prefix-and-suffix-search/description/)  


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


### 小结
+ TrieNode class 的表示方法有很多种，具体使用那一种要根据不同的题目做出分析，但无论何种表示，其内在的思想都是一样的
+ Trie树占用内存较大，例如：处理最大长度为20、全部为小写字母的一组字符串，则可能需要20^26个节点来保存数据。而这样的树实际上稀疏的十分厉害，可以采用需要多少子节点则添加多少子节点来解决（不要类似以上的示例，每个节点初始化时就申请一个长度为26的数组）。所以Trie相对适合用于**单词个数巨大，但平均单词长度很小的情况**。

### 其他相关题目
