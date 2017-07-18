# 二分法解析 --- 二维矩阵中的查找

## 设计题目
> [Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/#/description)  
> [Search a 2D Matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii/#/description)  
> [Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/#/description)  

## 思路分析
二维矩阵上的二分基本都是已经按照某种规律排序好的，关键是寻求如何将问题的规模减少。

### Search a 2D Matrix
题中的矩阵每一行都从左到右排序，并且下一行第一个数也比上一行最后一个大。可以看作是把一个排序数组中间切分又组成了矩阵。做法也与一维数组类似，不同的是要把一维的坐标转换为二维坐标，部分代码如下：

    while (start < end) {
        int mid = start + (end - start) / 2;
        if (matrix[mid/n][mid%n] < target) {
            start = mid + 1;
        } else if (matrix[mid/n][mid%n] > target) {
            end = mid - 1;
        } else {
            return true;
        }
    }
    return matrix[start/n][start%n] == target;
end初始化为 m * n - 1

### Search a 2D Matrix II
本题中矩阵的排序规则与上面稍有不同：每一行从左到右，每一列从上到下是排序好的，但并没有说每一行都比上一行大，只是对应列比上一行的大。

由于矩阵并非完全意义上的排序，取中点的操作并不能很好的帮助我们排除掉一部分选项。这时要将二分法思想扩展开来考虑。并非一定要一次性去掉一半才是二分，只要能通过O(1)的操作降低问题的规模就可以了，所以起点和终点的选择就尤为重要。对于这个矩阵的模型类似下图：

![](https://github.com/zhaoxy136/LeetCode/blob/master/Summary%20and%20Tricky%20tips/assets/sorted%20matrix.png)
可以发现右上角的位置很特殊，其向左递减，向下递增。换句话说，我们每次与右上角红色位置比较，如果val < target,说明这一行向左更不可能，我们可以将指针向下移动到桔色位置。  
如果val > target,说明这一列向下更不可能，我们将指针向左移动到黄色位置。  
如果val == target,则直接返回true即可。

代码实现如下：

    int row = 0;
    int col = matrix[0].length - 1;
    while (row < matrix.length && col >= 0) {
        if (matrix[row][col] < target) {
            row++;
        } else if (matrix[row][col] > target) {
            col--;
        } else {
            return true;
        }
    }
    return false;

### Kth Smallest Element in a Sorted Matrix
本题中矩阵的排序也是两个方向上的，即每一行只有对应列一定比上一行的大，并非比上一行所以元素都要大。

在尝试过右上角开始的思路后发现并不能奏效，因为我们要定位某一个位置的数而非具体某个值的数，由题目条件我们又无法得出比右上角的数大的或者小的数具体有多少个。故不得不寻求其他方式：既然按index不行，我们可以按range进行搜索，因为我们已知矩阵中最小的数一定在左上角，最大的数一定在右下角。

我们需要确定是这样一个值，满足矩阵中**小于等于**这个值的元素个数为k。当其小于k时，说明kth的数一定还在后面；如果等于k，则已经找到想要的答案；如果大于k，则往后一定更找不到，所以要向前找，但又不能直接跳过这个值，因为kth smallest的数可以重复很多遍。

**重要修正**上述思路中当满足count == k时，并不能直接得出结果，因为此时的value并不一定是矩阵中的元素，而仅仅是在矩阵范围内的一个数。如例子：  
matrix = [  
   [ 1,  5,  9],  
   [10, 11, 13],  
   [12, 13, 15]  
],  
k = 8  
当范围中搜索14时，返回count结果为8，但这时14并不是正确答案。我们只能另end=14，再进一步缩进。由于题目对k的限定，我们可以保证有答案，所以不必担心最终剩下的start也不在矩阵之中的考虑。代码实现如下：

    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        int start = matrix[0][0];
        int end = matrix[matrix.length-1][matrix[0].length-1];
        while (start < end) {
            int mid = start + (end - start) / 2;
            int count = check(matrix, mid);
            if (count < k) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
    private int check(int[][] matrix, int val) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            int j = matrix[i].length;
            while (--j >= 0 && matrix[i][j] > val);
            count += j+1;
        }
        return count;
    }


## 小结
+ 对于一个方向排序的矩阵，其解法与排序一维数组相似，对于两个方向的排序矩阵，一则可以利用右上角`分水岭`的特点，二则可以利用按范围搜索进行二分
+ 二分法的关键点在于确定**搜索空间**，常见的搜索空间有`index`和`range`,前者基本用于排序的序列，而对于未排序或者本身并不是序列的题目，只要我们知道搜索的上限和下限，也可以考虑用二分法进行解答。常见的使用`index`和`range`的题目分别有
 >> Search in rotated array,分析见[二分法解析(1)](https://github.com/zhaoxy136/LeetCode/blob/master/Summary%20and%20Tricky%20tips/Binary%20Search%20(1).md)  
 >> Find duplicate number,分析见[二分法解析(3)](https://github.com/zhaoxy136/LeetCode/blob/master/Summary%20and%20Tricky%20tips/Binary%20Search%20(3).md)
+ 我们发现，往往当题目要求search具体值的时候，我们采用按index搜索；当要求search一个具体位置的数时，我们采用按范围搜索。

## 其他相关题目


