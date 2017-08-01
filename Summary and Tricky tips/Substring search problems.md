# 子字符串搜索问题（找满足特定条件的substring）

## 涉及题目
> [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/description/)  
> [Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/description/)  
> [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/description/)  
> [Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/)   

## 思路分析
这类题目的共性如题所说，就是给出一个字符串，找出其子满足特定条件的子字符串。对于搜索问题，这类题目的**搜索空间**非常明确就是字符串。  
而这个特定条件可以**直接说明**，也可以**通过另一个字符串来规定**。我们之后将会看到两种不同的方式给出的要求条件。

对于这类题网上也有大神总结出了模版！首先指明我的参考出处：
+ [10 line template](https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems)  
+ [Sliding Window algorithm template](https://discuss.leetcode.com/topic/68976/sliding-window-algorithm-template-to-solve-all-the-leetcode-substring-search-problem)

两个模版的思路基本一样，都是hashmap + two pointers的模式。首先令end指针不断向前更新，当

首先看java书写的常规模版：

    public List<Integer> slidingWindowTemplateByHarryChaoyangHe(String s, String t) {
        //init a collection or int value to save the result according the question.
        List<Integer> result = new LinkedList<>();
        if(t.length()> s.length()) return result;
        
        //create a hashmap to save the Characters of the target substring.
        //(K, V) = (Character, Frequence of the Characters)
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        //maintain a counter to check whether match the target string.
        int counter = map.size();//must be the map size, NOT the string size because the char may be duplicate.
        
        //Two Pointers: begin - left pointer of the window; end - right pointer of the window
        int begin = 0, end = 0;
        
        //the length of the substring which match the target string.
        int len = Integer.MAX_VALUE; 
        
        //loop at the begining of the source string
        while(end < s.length()){
            
            char c = s.charAt(end);//get a character
            
            if( map.containsKey(c) ){
                map.put(c, map.get(c)-1);// plus or minus one
                if(map.get(c) == 0) counter--;//modify the counter according the requirement(different condition).
            }
            end++;
            
            //increase begin pointer to make it invalid/valid again
            while(counter == 0 /* counter condition. different question may have different condition */){
                
                char tempc = s.charAt(begin);//***be careful here: choose the char at begin pointer, NOT the end pointer
                if(map.containsKey(tempc)){
                    map.put(tempc, map.get(tempc) + 1);//plus or minus one
                    if(map.get(tempc) > 0) counter++;//modify the counter according the requirement(different condition).
                }
                
                /* save / update(min/max) the result if find a target*/
                // result collections or result int value
                
                begin++;
            }
        }
        return result;
    }
    
然后是利用数组来替代hashmap的版本，原因是string中可能的字符都是ASCII编码范围内，所以长为128的数组可以实现对应的查找和储存功能。

    int findSubstring(string s){
        vector<int> map(128,0);
        int counter; // check whether the substring is valid
        int begin=0, end=0; //two pointers, one point to tail and one  head
        int d; //the length of substring

        for() { /* initialize the hash map here */ }

        while(end<s.size()){

            if(map[s[end++]]-- ?){  /* modify counter here */ }

            while(/* counter condition */){ 
                 
                 /* update d here if finding minimum*/

                //increase begin to make it invalid/valid again
                
                if(map[s[begin++]]++ ?){ /*modify counter here*/ }
            }  

            /* update d here if finding maximum*/
        }
        return d;
    }

### Minimum Window Substring
题目给出字符串S和T，我们的搜索空间是S，T的作用就是提供了搜索应满足的要求，即window中应包含所有T中的字符。
按照第二个模版来书写，基本完全套用，代码如下：

    public String minWindow(String s, String t) {
        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++;
        }
        int start = 0, end = 0, head = 0;
        int count = t.length();
        int len = Integer.MAX_VALUE;
        while (end < s.length()) {
            if (map[s.charAt(end++)]-- > 0) count--;//in t
            while (count == 0) {//become valid
                if (end - start < len) {
                    len = end - start;
                    head = start;
                }//update result
                if (map[s.charAt(start++)]++ == 0) count++;//become invalid
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(head, head+len);
    }

### Find All Anagrams in a String
题目依然给出S和P两个字符串，要求在S中找出所有P的同字母异序词。  
与上一题不同的是，此题要求返回所有的可能性，而不是返回最优解（最小或最大）。但要注意的是，我们应判定end-start是否刚好是P的长度。代码如下：

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) return res;
        int[] map = new int[128];
        for (char c : p.toCharArray()) {
            map[c]++;
        }
        int start = 0, end = 0, count = p.length();
        while (end < s.length()) {
            char c = s.charAt(end++);
            if (map[c]-- > 0) count--;
            while (count == 0) {
                if (end - start == p.length()) res.add(start);
                char ch = s.charAt(start++);
                if (map[ch]++ == 0) count++;
            }
        }
        return res;
    }

### Longest Substring Without Repeating Characters
本题给出一个字符串S，即为搜索空间。我们需要找出其含有不重复字符的最长子字符串。属于直接给出搜索规则的题目。  
我们依然可以利用模版中的count来统计出**重复出现的是哪个字符**，count等于0是合法状态（valid），可以不断更新字符串长度。一旦count > 0，我们需要
移动start指针，直至map中原本大于1的（等于2）的对应字符再次被找到，count--为0.又回到合法状态。代码如下：

    public int lengthOfLongestSubstring(String s) {
        int[] map = new int[128];
        int start = 0, end = 0, count = 0, max = 0;
        while (end < s.length()) {
            if (map[s.charAt(end++)]++ > 0) count++;
            while (count > 0) {
                if (map[s.charAt(start++)]-- > 1) count--;
            }
            max = Math.max(max, end-start);
        }
        return max;
    }

### Substring with Concatenation of All Words



## 其他相关题目
> [Longest Substring with At Most Two Distinct Characters](https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/description/)  
