## 涉及题目
>[Classic Binry Search](http://www.lintcode.com/en/problem/classical-binary-search/#)  
>[Search For a Range](https://leetcode.com/problems/search-for-a-range/#/description)     
>[Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/#/description)  

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
### Search Range--允许duplicates的排序数组， 找上下界
这道题要拆分为两个部分，求上界和下界。进一步转化就是，找第一个target出现的位置和最后一个target出现的位置。

首先，九章模版对此题覆盖，可安心食用。  
对于自定义二分，无论找first position 还是 last position，在nums[mid] == target 时*都不能安心跳过*，因为这个位置可能就是最后的结果。而又不同的是，对于找first position，相等时可令 end = mid; 但找last position时又无法令 start = mid(如果这样做会出现死循环！) 这是为什么呢？关键就在于:

    int mid = start + (end - start) / 2;
当范围内还有奇数个元素时，中点的坐标是准确的，但当范围是偶数，即start+end是奇数时，由于结果只保留整数部分，*这时确定的中点其实是两个中间位置中靠前的那一个*，所以在候选只有两个的时候(一个start，一个end)，循环并不会跳出，但是mid计算出来就会等于start，这也说明了若将start = mid，则会出现一直循环下去的结果。  
回到找first position和last position的区别上，找first情况，出现mid位置满足条件，即nums[mid] == target时，end = mid；由上述分析我们已知不会出bug。部分代码如下：

    while (start < end) {
        int mid = start + (end - start) / 2;
        if (nums[mid] < target) {
            start = mid + 1;
        } else if (nums[mid] > target) {
            end = mid - 1;
        } else {
            end = mid;
        }
    }
    return nums[start] == target ? start : -1;
但是在找last的情况下，出现mid位置满足条件时，start = mid就会出错，所以我们此时必须跳过start，令start=mid+1.从而在循环结束时，再判断start-1的位置是否满足。代码如下：

    while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        if (nums[start] == target) {
            return start;
        } else if (start > 0 && nums[start-1] == target) {
            return start - 1;
        }
    return -1;
此前我们也说了，导致这么别扭的结果的罪魁祸首就是除法的结果只保留整数导致的，那么我们求mid时采用 mid = start + (end + 1 - start) / 2;是不是可以避免呢？即我们总将mid定位在偏后的位置，这样就可以安心另start = mid了。**要注意的是，这样的话end = mid就会导致错误啦！**

    while (start < end) {
        int mid = start + (end + 1 - start) / 2;
        if (nums[mid] < target) {
            start = mid + 1;
        } else if (nums[mid] > target) {
            end = mid - 1;
        } else {
            start = mid;
        }
    }
    return nums[start] == target ? start : -1; 
到此我们分析出了导致死循环的方法，以及循环体内更新start和end时要分析mid位置能否安全跳过

### Sorted Array--Find Minimum Element
接下来我们考虑一个rotated后的排序数组，即数组被整体平移过了，它的最大值可能在中间某个位置。这样导致了数组内的连续递增性遭到了破坏。
这道题我们可以很快确定如果想在O(log(n))的时间复杂度下完成一定要用到二分法。接下来要考虑的就是如何转化题目：
1. 找到第一个小于nums[0]的位置
2. 找到第一个小于nums[nums.length-1]的位置
3. 找到nums[i] < nums[i-1]的位置
经过尝试，方法1和方法3都需要

    while (start < end) {
        int mid = start + (end - start) / 2;
        if (nums[mid] < nums[0]) {
            end = mid;
        } else {
            start = mid + 1;
        }
    }
    if (nums[start] < nums[0]) return nums[start];
    return nums[0];


    while (start < end) {
        int mid = start + (end - start) / 2;
        if (nums[mid] <= nums[nums.length -1]) {
            end = mid;
        } else {
            start = mid + 1;
        }
    }
    return nums[start];



## 小结
由此上分析我们分别找出了结症所在以及一些结论：
+ 如果while循环判定为 **start < end** 的话，则循环体内**一定不能有 `start = mid` 语句**, 否则会导致死循环！
+ 导致上述死循环的原因是计算mid位置的方法---当有两个中点时总是选择靠前的一个，因此可以另mid=start + (end+1-start)/2 来锁定靠后的那个，从而放心使用start=mid，代价是不能使用end=mid。 往往找first position时按正常习惯写，找last position时将mid取在靠后位置。

## 其他相关参考题目
>[First Bad Version](https://leetcode.com/problems/first-bad-version/#/description)  
>[Search Insert Position](https://leetcode.com/problems/search-insert-position/#/description)  
>[Find Peak Element](https://leetcode.com/problems/find-peak-element/#/description)  
