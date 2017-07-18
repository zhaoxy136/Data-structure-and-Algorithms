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





## 小结











## 其他相关题目


