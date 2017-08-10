![](https://github.com/zhaoxy136/LeetCode/blob/master/Stack%20And%20Heap/Task%20Scheduler/task%20scheduler.png)

首先我们按照字母出现的频率排序，根据最大的频率得出上图中所示的frame，阴影部分即为可能的idle回合。所有的字母不可能超出这个frame，所以我们只需要比较
frame的大小和tasks的总数。那么较大的那个一定就是最终的count啦！
