# Some Problems about Interval

## 涉及题目
> [Meeting Rooms](https://leetcode.com/problems/meeting-rooms/description/)  
> [Merge Intervals](https://leetcode.com/problems/merge-intervals/description/)  
> [Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/description/)  
> []()  


## 思路分析
对于Interval的问题，往往属于Greedy的思路。最典型的就是会议室安排问题,如何安排才能充分利用公共资源。尽可能多的安排会议。按照贪心的思路一定
就是尽量先安排时间短的会议。但有的会议时间虽长但不与其他冲突，则还是应优先安排。那我们该如何定义**优先级**呢？---一种是按开始时间排序，一种
按结束时间排序。那么下面我们结合题目讨论一下两种方案哪个更优。

### Meeting Rooms
题目要求判断给出的所有interval是否包含冲突，我们就假设没有冲突来给他们排个序，再看看有无矛盾。  
按start排序：

    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, (Interval a, Interval b) -> (a.start - b.start));
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < intervals[i-1].end) return false;
        }
        return true;
    }
按end排序：

    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, (Interval a, Interval b) -> (a.end - b.end)); //只有此处不同
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < intervals[i-1].end) return false;
        }
        return true;
    }

所以从本例来看，两种方案基本等价。

### Merge Intervals
题目要求将给出的所有intervals合并，并返回一个新的List。既然要合并，那么**必然先要判断是否有重叠**，这与上一题是一样的。不同的是，对待重叠的处理方式，
不是return false，而是将重叠部分合并，继续向后判断。而没有重叠时则直接将当前interval放入结果List中即可。对于本题，按照start或是end排序也并没有
什么不同。

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals.size() == 0) return res;
        PriorityQueue<Interval> queue = new PriorityQueue<>(new Comparator<Interval>(){
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        for (Interval v : intervals) {
            queue.add(v);
        }
        Interval prev = queue.poll();
        while (!queue.isEmpty()) {
            Interval tmp = queue.poll();
            if (tmp.start <= prev.end) {
                prev = new Interval(prev.start, Math.max(prev.end, tmp.end));
            } else {
                res.add(prev);
                prev = tmp;
            }
        }
        res.add(prev);
        return res;
    }
    
### Non-overlapping Intervals
题目要求至少去除多少interval才能避免冲突。 题目其实也等价于用一个会议室最多能支持几个会议完成，即相互不冲突的interval最多多少个。  
本题看似是一道动态规划，其实仔细分析可以发现我们用greedy的思想就可以解决：为尽可能多的分配会议，那些占用资源少的应该最先被安排。如果按照start排序，
我么先选出最先开始的，然后和第二早开始的最比较，两者保留较早结束的一个；如果按照end排序，则我们可以直接确定最早结束的一定优先选中，然后再选出在他
结束之后最早开始的一个。所以这题两个方案相比，虽然都可行，但按照end排序似乎更便捷一些！  
注意，题目问的是最少去掉多少个，则需要用总的interval数减去最多保留的数目。

    public int eraseOverlapIntervals(Interval[] intervals) {
        int count = intervals.length;
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.end - b.end;
            }
        });
        int last = Integer.MIN_VALUE;
        for (Interval inter : intervals) {
            if (inter.start >= last) {
                count--;
                last = inter.end;
            }
        }
        return count;
    }





## 小结
+ 与Interval有关的题目，大多数都是greedy的思想，基本需要先进行排序。

## 其他相关题目
> []()  
> []()  
> []()  
> []()  
