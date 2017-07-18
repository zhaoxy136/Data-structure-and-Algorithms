# 二分法解析 --- 一维排序数组上的二分

## 涉及题目
>[Classic Binry Search](http://www.lintcode.com/en/problem/classical-binary-search/#)  
>[Search For a Range](https://leetcode.com/problems/search-for-a-range/#/description)     
>[Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/#/description)  
>[Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/#/description)  
>[Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/#/description)

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
    return nums[end] == target ? end : -1; //还要注意的就是，此时start不一定是候选范围内的，而end一定是。所以最后要比较end位置是否满足条件。
到此我们分析出了导致死循环的方法，以及循环体内更新start和end时要分析mid位置能否安全跳过

### Sorted Array--Find Minimum Element
接下来我们考虑一个rotated后的排序数组，即数组被整体平移过了，它的最大值可能在中间某个位置。这样导致了数组内的连续递增性遭到了破坏。
这道题我们可以很快确定如果想在O(log(n))的时间复杂度下完成一定要用到二分法。接下来要考虑的就是如何转化题目：
1. 找到第一个小于nums[0]的位置
2. 找到第一个小于nums[nums.length-1]的位置

方法1还需要考虑如果while出来后nums[start] > nums[0]，则说明数组没有rotated，需返回nums[0]，部分代码如下：

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
方法2则不需要分开考虑，不论数组有没有rotate，第一个小于nums[len-1]的数一定就是最小值：

    while (start < end) {
        int mid = start + (end - start) / 2;
        if (nums[mid] <= nums[nums.length -1]) {
            end = mid;
        } else {
            start = mid + 1;
        }
    }
    return nums[start];

### Search in Rotated Sorted Array
接下来我们进一步分析如何在rotated array里进行查找。  
首先，由上面的找起始点问题得到启发，我们可以先用一个二分找到最小位置，从而确定target在其左或者右。然后再用一个二分进行查找。  
但我们也可以用一次二分来对其解决。如下图所示  
在此再提一下二分法的思想核心：通过O(1)的操作将问题的规模减半，或按比例降低。通俗地讲，找到一个标准使原问题去掉一半。
![](https://github.com/zhaoxy136/LeetCode/blob/master/Summary%20and%20Tricky%20tips/assets/Search%20in%20rotated%20array.png)
对于rotated array，mid的划分有两种可能，即上图中的红线和绿线所示。而对于这两种情况我们要分别讨论。过程中不仅要比较target和nums[mid],还要借助nums[start]和nums[end]来完成。代码实现如下：

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] == target) return mid;
            if (nums[mid] > nums[end]) { //注意，对于rotated array，要用mid和end做比较，这一点从上一点也可以看出。
            //red line
                if (target < nums[mid] && nums[start] <= target) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
            //green line
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return nums[start] == target ? start : -1;
    }

这时候再返回看rotated array找最小值的问题，可以发现，它相当于search的一种特殊情况。我们按照相同的思考方式做改写：

    while (start < end) {
        int mid = start + (end - start) / 2;
        if (nums[mid] > nums[end]) {
            start = mid + 1;//此时mid必定不是最小值，可以跳过 
        } else {
            end = mid;//因为mid位置可能就是最小值所以不能直接舍去 
        }
    }
    return nums[start];

### Search in Rotated Sorted Array II
这道题的思路与I基本一样，唯一不同的是：由于允许重复的存在，最坏情况是无法通过二分来找到的。可以想象111...0..11的情况（只有一个0）  
但我们还是希望给出二分的解法，因为这样平均时间复杂度会更低：

    while (start < end) {
        int mid = start + (end - start) / 2;
        if (nums[mid] == target) return true;
        if (nums[mid] > nums[end]) {  
            //可以确定“断层”在mid的右侧，然后再分情况讨论  
            if (nums[start] <= target && target < nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        } else if (nums[mid] < nums[end]) {
            //可以确定mid一定在右侧，即绿线区域 
            if (nums[mid] < target && target <= nums[end]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        } else {
            //到这一步只能得出nums[end] == nums[mid] && nums[mid] != target,所以只能排除end位置，以防最坏情况出现 
            end--;
        }
    }
    return nums[start] == target;


## 小结
由此上分析我们分别找出了结症所在以及一些结论：
+ 如果while循环判定为 **start < end** 的话，则循环体内**一定不能有 `start = mid` 语句**, 否则会导致死循环！
+ 导致上述死循环的原因是计算mid位置的方法---当有两个中点时总是选择靠前的一个，因此可以另mid=start + (end+1-start)/2 来锁定靠后的那个，从而放心使用start=mid，代价是不能使用end=mid。 往往找first position时按正常习惯写，找last position时将mid取在靠后位置。
+ 对于rotated array，要根据mid切在哪一部分而做不同分析。而判断的依据是用mid和end做比较，**而不是**与start比较。 从而打到去掉一半的目的。

## 其他相关参考题目
>[First Bad Version](https://leetcode.com/problems/first-bad-version/#/description)  
>[Search Insert Position](https://leetcode.com/problems/search-insert-position/#/description)  
>[Find Peak Element](https://leetcode.com/problems/find-peak-element/#/description)  
