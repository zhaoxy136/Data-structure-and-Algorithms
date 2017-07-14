## 涉及题目
>[Classic Binry Search](http://www.lintcode.com/en/problem/classical-binary-search/#)  
>[Search For a Range](https://leetcode.com/problems/search-for-a-range/#/description)  
>[Search Insert Position](https://leetcode.com/problems/search-insert-position/#/description)  
>[First Bad Version](https://leetcode.com/problems/first-bad-version/#/description)  

## 思路分析
__二分法的思想虽然容易理解，但不同问题的具体实现却没有一个可以通用又简便的模版。接下来，我通过若干道二分法的问题来对该方法做出总结：__

首先来声明两种解题框架，他们的分支是从`while`循环的判定条件开始的：一种九章算法中提出的通用模版：

    while (start + 1 < end) {
      int mid = start + ((end - start) >> 1); //注意这里如果用移位操作的话一定要多加一个()，因为>>的优先级较低
      if (nums[mid] < target) {
        start = mid;
      } else if (nums[mid] > target) {
        end = mid;
      } else {
        return mid;
      }
    }
    //循环结束后，依然有两个候选，`start` 和 `end`, 所以仍需判断start 和 end谁满足条件，但方便的是可以确定结果一定在二者之中，或者根本没有答案。

和第一类的些许应试意味不同，第二类更像是解决问题的，但由于循环内部写法不统一，很多时候容易出错：

    while (start < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] < target) {
        start = mid + 1;
      } else if (nums[mid] > target) {
        end = mid - 1;
      } else {
        return mid;
      }
    }
    //循环结束只需判断start是否满足条件即可
可见，无论是哪种方法，代码量都不大，但就这么几行代码为什么会错误频发呢？ 我们通过题目来进一步探索。  



## 小结
由此上分析我们分别找出了结症所在以及一些结论：
+ 如果while循环判定为 **start < end** 的话
+ 



