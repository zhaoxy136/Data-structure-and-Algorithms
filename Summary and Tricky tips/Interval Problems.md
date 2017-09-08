# Some Problems about Interval

## 涉及题目
> [Meeting Rooms](https://leetcode.com/problems/meeting-rooms/description/)  
> [Merge Intervals](https://leetcode.com/problems/merge-intervals/description/)  
> [Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/description/)  
> [Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/description/)  
> [Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/description/)  



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
不是return false，而是将重叠部分合并，继续向后判断。而没有重叠时则直接将当前interval放入结果List中即可。  
在排序时，如果按照end排序，则以下例子会出现错误{[2, 4], [5, 6], [1, 8]}。**故此题需要按照start排序**

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
题目要求至少去除多少interval才能避免冲突。 题目其实也等价于用一个会议室最多能支持几个会议完成，即相互不冲突的interval最多有多少个。  
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

### Minimum Number of Arrows to Burst Balloons
题目要求得出最少多少根针可以戳破所有气球，放佛是重叠的越多越好，与上述系列问题反其道而行之的感觉，但经过分析和此类题目的经验，我们便可大胆预测需要先排序，先按照end排序，则在第一个结束的interval的end节点处放针，肯定不会错过其他任何气球，并且将所有在这一针范围内的气球全部爆掉。  
如果按照start排序，则与上一题相似，虽然能做出来，但还是要对end的位置前后做比较。

    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) return 0;
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        int res = 1;
        int last = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > last) {
                res++;
                last = points[i][1];
            }
        }
        return res;
    }

### Meeting Rooms II
本题与上述题目不同的是，依然考虑最优解，但求出至少几个会议室可以安排全部会议。我们依然按照end排序，并且利用一个PriorityQueue来维持**所有的会议室
当前安排的会议结束节点**，下一个与当前所有会议室中结束最早的一个不冲突，则将其安排进去并更新结束时间；否则必须新开一个会议室，即向Queue中add一个新的end。最终queue的长度即为会议室的数量：

    public int minMeetingRooms(Interval[] intervals) {
        int res = 0;
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        for (int i = 0; i < intervals.length; i++) {
            if (!queue.isEmpty() && intervals[i].start >= queue.peek()) {
                queue.poll();
            }
            queue.offer(intervals[i].end);
        }
        return queue.size();
    }


## 小结
+ 与Interval有关的题目，大多数都是greedy的思想，基本需要先进行排序，排序按照interval的开始或者结束节点进行；
+ 虽然一些题目按照开始或结束点排序没有区别，大部分题目按照end排序会更清晰一点；


## 其他相关题目
> [Find Right Interval](https://leetcode.com/problems/find-right-interval/description/)  
> [Insert Interval](https://leetcode.com/problems/insert-interval/description/)  
> []()  
> []()  
