# 二分法解析 --- 隐式二分法

## 涉及题目
> [Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/#/description)  
> [Sqrt(x)](https://leetcode.com/problems/sqrtx/#/description)  


## 思路分析
对于一些题目，提供的数组或序列并没有排序处理。在限定了时间及空间复杂度的情况下（例如原本需要O(n)或者O(n2)的时间完成的，又要求限定空间O(1)时进一步优化 )，我们既无法借助数据结构来降低空间，那么只能将时间向对数级别降低，最常用的方法就是`二分法`了。  
经过分析，这一类的题目本身也透露着某些共同的特点。同样，我们还是通过几道题目来探索其中端倪。

### Find the Duplicate Number
首先这道题目有几个限定条件：
 + 数组read-only，既不能排序，也不能利用在数组本身施加标记来check
 + 要求O(1)的额外空间，这排除了使用数据结构的可能性
 + 要求小于O(n2)的时间，其隐含条件基本是O(nlog(n))的限制了
 
到此好像也并无法得出二分法的思路，但题目中还有一些特点做出了提示：
 + 数组内整数范围与数组长度的关系：范围1-n(n种候选)，长度n+1。即说明至少有一个重复
 + 题目明确说到，只有一个重复

到此我们基本可以确定可以使用二分法了，因为二分的思想是一次去掉不可能的一半，或者保留可能的一半。对于这道题的情况，虽然数组没有排序，但我们知道了其中元素的上下界。并且我们可以通过检验数组内小于等于某值x的元素个数来确定1~x中有没有可能出现重复。

假设数组内小于等于x的元素个数大于了x，显然这其中一定有重复的存在。而排除了x+1,x+2..n的可能性，我们达到了去掉一半的目的。但注意此时不可直接排除x。

假设数组内小于等于x的元素个数小于等于x，虽不能直接说明1,2..x没有重复(因为题目没有限定每个数字都要出现)，但反观x,x+1,...n的数量一定会是超出n-x的，即说明重复的数肯定不在前x个数中，这时我们同样去掉了一半的可能性。

代码实现如下：

    public int findDuplicate(int[] nums) {
        int start = 1;
        int end = nums.length-1; // end = n
        while (start < end) {
            int mid = start + (end - start) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            if (count <= mid) {
                start= mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }

### Implement int sqrt(int x)
题目要求很简单，即求一整数的平方根。这道题也没有清晰的提示用二分法来解决，但很明显如果从小到大一个一个试确实是比较慢的，很自然的会想到先在中间找个数试一试。那么1～x其实就构成了一种**隐含排序**，我们可进一步把题目转化为：在从1到x之间，找到最后一个乘方小于等于x的数。这不就是二分法的标准表述吗？

    public int mySqrt(int x) {
        if (x <= 1) return x;
        int start = 1, end = x;
        while (start < end) {
            int mid = start + (end + 1 - start) / 2;
            if (x / mid == mid) return mid;
            if (x / mid < mid) {
                end = mid - 1;
            } else {
                start = mid;
            }
        }
        return end;
    }
要提醒注意的一点是，本题是找last position，所以注意取中点时应取靠后的那一个，来避免死循环。

### Find Right Interval
本题第一反应应该是要排序，但要注意的是要求返回原来顺序的结果，所以排序不能在原数组内进行。本题由于start起点是各不相同的，故应该对start进行排序，排序结束后可再利用二分去查找满足条件的interval，即第一个start点大于等于所求区间end的interval。部分代码如下：

    public int[] findRightInterval(Interval[] intervals) {
        int[] res = new int[intervals.length];
        Map<Integer, Integer> map = new HashMap<>();
        int[] starts = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i].start;
            map.put(starts[i], i);
        }
        Arrays.sort(starts);
        for (int i = 0; i < intervals.length; i++) {
            res[i] = map.getOrDefault(binarySearch(starts, intervals[i].end), -1);
        }
        return res;
    }



## 小结
+ 许多unsorted或者无法排序的数组，特别是在数组元素范围已知的情况下，可以考虑二分。能否得以实施关键要找到能否通过某些标准去除掉一半。
+ 对于本身就不是数组的问题，如果是自然数1～n，且很容易确定界限时，也应该考虑二分。关键考察结果满足怎样的标准
+ 对于没有限制排序的题目，可以先排序。若排序后还需要O(n2)的复杂度时，可以考虑通过二分降低至O(nlog(n))。**且往往这种排序与二分结合的例子，可以通过数据结构TreeSet或TreeMap来实现，只不过需要额外的空间。







## 其他相关题目
> [Valid Perfect Square](https://leetcode.com/problems/valid-perfect-square/#/description)  
> [Heaters](https://leetcode.com/problems/heaters/#/description)  
> []()  
>





