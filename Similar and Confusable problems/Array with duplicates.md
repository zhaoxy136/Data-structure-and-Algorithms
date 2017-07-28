## 题目特征
本次我们要讨论的是和duplicate有关的题目，题目的体征有2点：
+ 大多数是基于数组的
+ 题目往往直接说明了意图，找到duplicate或not appearred

## 涉及题目
> [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/tabs/description)  
> [Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/tabs/description)  
> [Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii/tabs/description)  
> [Single Number](https://leetcode.com/problems/single-number/tabs/description)  
> [Missing Number](https://leetcode.com/problems/missing-number/tabs/description)  
> [Find All Duplicates in an Array](https://leetcode.com/problems/find-all-duplicates-in-an-array/tabs/description)  
> [Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/tabs/description)  
> [Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/tabs/description)  
> [First Missing Positive](https://leetcode.com/problems/first-missing-positive/tabs/description)  

## 思路分析
在此我们只分析题目间的异同和大体思路，不再分析代码。

### Contains Duplicate
题目要求：给出一个数组，返回其中是否含有重复。  
思路 1.可以对数组进行排序，然后依次判断有没有连续的相同元素；  
思路 2.使用hashset检测是否有重复；

### Contains Duplicate II
题目要求：给出一个数组，返回其中是否含有重复，限定为两个元素的index之差不得大于k。  
思路 1.使用hashmap，key代表元素的值，value为对应的index，当遇到相同的值时在检测index之差是否小于等于k，再对其进行更新；  
思路 2.我们可以看作是sliding window中找相同的元素，window的宽度为k。故依然可以使用hashset，需要改动的是当set的size大于k时，将nums[i-k]剔除。


### Contains Duplicate III
题目要求：给出一个数组，返回其中是否含有相差不大于t的两个元素，限定为两个元素的index之差不得大于k。  
思路：我们不再判定重复，而是在t范围上下都可以。所以此时我们应判断的是window内最相近的元素之差是否满足即可，这便提出了排序的需要。故此题我们借助treemap。

### Single Number
题目要求：给出一个数组，其中除一个元素外所有数都出现两次。找出这个唯一的元素。 O(1)space  
思路：利用x^x = 0的按位异或思路，将所有元素异或，剩下的答案就是唯一的distinct数；  

### Find All Duplicates in an Array
题目要求：给出一个长度为n的数组，元素大小在1-n范围内，有些出现两次有些出现一次，求所有出现过两次的元素。O(1)space  
思路：由于数组长度和其中元素的大小范围相同，并且都是正数。我们便可以利用数组本身来做标记。每当一个数出现，就将其*绝对值*对应下标位置的元素置负，当该元素已经为负数，则该数为重复出现的。

### Find All Numbers Disappeared in an Array
题目要求：给出一个长度为n的数组，元素大小在1-n范围内，有些出现两次有些出现一次，求所有没出现过的元素。O(1)space  
思路：与上一题完全相同，通过将对应下标置负来标记是否出现过

### Find the Duplicate Number
题目要求：给出一个长度为n+1的数组，其中元素范围是1-n。已知其中仅有一个元素多次出现。请找出这个数。O(1)space且不能对数组有改动  
思路：此题与之前两题十分相似，但**如果通过相同方法解决的话就需要对数组进行改动。**不符合要求。  
但由于其中的元素范围上下限已知我们可以通过二分法解决，在此就不再赘述。具体可以参照[二分法3](https://github.com/zhaoxy136/LeetCode/blob/master/Summary%20and%20Tricky%20tips/Binary%20Search%20(3).md)

### Missing Number
题目要求：给出一个长为n的数组，其中元素范围0-n，没有重复元素。请找出没有出现的那一个。O(1)space  
注意，**由于此题数组长小于范围，故无法用下标来作记号**  
思路 1.由于我们可以确定有且仅有一个数没出现，且已知全部出现的话是从0-n，可以用求和的数学方法解决；  
思路 2.依然可以使用按位异或的方法，不过这次异或的是所有的数和所有下标+1. 相当于把题目转化成了长为2n+1，除答案外每个数都出现两次。  
思路 3.由于本题允许更改数组内容，我们可以直接排序然后用二分去找那个位置空缺。

### First Missing Positive
题目要求：给出一个数组，可能有正数和负数，找出第一个不存在的正数。O(n)time， O(1)space
思路1：此题更加隐晦，是上一题更进一步的问法。由于时间复杂度限制我们不能排序。  
我们希望能尽量向上一道题靠拢，所以先排除负数的影响。对此使用partition算法把正负数分开，同时我们也得到了有多少个正数，假设为k。那么答案一定在1-k+1之间。
由于数组长度一定是大于等于k的，我们有回到了Find All Duplicates in an Array的思路，对正数依次check，小于k的话就将对应位置的正数置负。最后第一个没被置负的
数即为答案，如果全被置负，则答案为k+1。部分代码如下：

    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        int i = -1, j = 0;
        while (j < nums.length) {
            if (nums[j] <= 0) {
                int tmp = nums[++i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
            j++;
        }
        int k = nums.length - (i + 1);
        for (int l = i+1; l < nums.length; l++) {
            if (Math.abs(nums[l]) <= k) {
                int index = i + Math.abs(nums[l]);
                if (nums[index] > 0) nums[index] *= -1;
            }
        }
        for (int l = i+1; l < nums.length; l++) {
            if (nums[l] > 0) return l-i;
        }
        return k+1;
    }

思路2: 我们更进一步简化，同样由**答案一定小于等于n，n为数组长度**得出，我们可以将所有小于等于n的正数放在其对应位置上。然后再遍历，直至第一个对应位置数
没有出现的即为答案。部分代码如下：

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i]-1] != nums[i]) {
                int tmp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = tmp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i+1) return i+1;
        }
        return n+1;
    }















