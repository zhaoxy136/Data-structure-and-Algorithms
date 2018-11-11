## 关于快速排序中partition算法的分析

### 涉及题目
> [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/description/)

### 思路分析
首先，我们已知的分割方法有两种：Hoare和Lomuto算法,我们就先来看看两种方法在quick-sort中的表现吧。  

Lomuto:

    partition(arr[], lo, hi) 
    pivot = arr[hi]
    i = lo - 1     // place for swapping
    for j := lo to hi – 1 do
        if arr[j] <= pivot then
            swap arr[++i] with arr[j]
    swap arr[i] with arr[hi]
    return i

    
Hoare:
```
   partition(arr[], lo, hi)
   pivot = arr[lo]
   i = lo - 1  // Initialize left index
   j = hi + 1  // Initialize right index

   // Find a value in left side greater
   // than pivot
   do
      i = i + 1
   while arr[i] < pivot

   // Find a value in right side smaller
   // than pivot
   do
      j = j - 1
   while arr[i] > pivot

   if i >= j then 
      return j

   swap arr[i] with arr[j]
```
以一组数据来举例：[5,3,1,6,2,4,3,5]  
通过Lomuto做一次分割后变成[5,3,1,2,4,3,5 | 6],且返回index为6。  
通过Hoare做分割后结果为[5,3,1,3,2,4 | 6,5],且返回index为5.

以上两种算法的区别主要在于3点：  
+ 两个指针初始位置不同；  
+ pivot的选取不同；
+ 遍历过程中与pivot的比较条件不同(<= or </>)

接下来我们就来讨论这三点不同分别造成的影响是什么。

指针的遍历方向我们暂不做分析，且视为两个算法的主要区分点，那么pivot的选取有什么区别呢？为什么一个要选last element另一个却选择了first element呢?

假设我们把Lomuto的pivot选为first element，则针对[5,3,1,6,2,4,3,5]得到的结果为[5,3,1,2,4,3,5 | 6],返回index 6。
接着我们依然针对Lomuto将遍历过程中arr[i] <= pivot 换成 arr[i] < pivot,则针对例子[5,3,1,6,2,4,3,5]选择5为pivot得到[3,1,2,4,3 | 6,5,5]。
通过对比发现，对于pivot算法，无论如何选取pivot的值，数组内pivot等值的元素一定会被分到同一侧。而根据**交换条件**不同，分割两侧可能为严格大于pivot或者
大于等于pivot。

我们对Hoare做同样的变换，首先对于例子[5,3,1,6,2,4,3]，选择last element3作为pivot，得到结果[3,2,1 | 6,3,4,5]。  
再次，以[5,3,1,6,2,4,3,5]为例，将交换条件换为`i每次找到严格大于pivot的位置，而j每次找到小于等于pivot的位置`，则得到的结果为[5,3,1,5,2,4,3 | 6],
一样得到了类似于Lomuto的分割结果。

所以只要搞懂了两种方法里指针的意义，就可以很好的操作和修改算法从而得到自己想要的结果。  
Lomuto中i一直记录的是目前为止最后一个小于等于pivot的位置；Hoare算法中i，j表示从两侧出发第一个大于／小于pivot的位置，然后将两者交换。  

还有一点需要注意的是，如果数组本身已经接近排序，则在遍历过程中Lomuto算法的交换次数会远远高于Hoare算法！

### 小结
+ 对于Lomuto算法，基础模版得到分割左侧为小于等于pivot的元素，分割右侧为严格大于pivot的元素。我们可以通过修改**交换条件**从而使分割右侧变为
大于等于，左侧为严格小于。
+ 对于Hoare算法如果我们修改**交换条件**则可以得到与上述同样的结果。  
+ 从效率上来说，从两端向中间逼近需要的交换次数相对较少，但并不是stable的。而Lomuto却能保证交换后pivot两端的元素相对位置不变，有时是什么重要的！  
+ 对于两种算法只要搞懂了指针所代表的意义，便可以更好的理解算法本质。


