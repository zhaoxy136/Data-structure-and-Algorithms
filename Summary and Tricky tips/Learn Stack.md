# 了解Stack的魅力
stack听起来或许不陌生，但真正能理解它又不是一件容易的事。Stack在计算机编程发展中起到了举足轻重的地位，这种数据结构也是在任何程序执行的时候都会用到的。
既然如此，我们今天就来看一看究竟stack有什么魅力，它能帮助我们有效地处理哪些问题，它的特点和优势又是什么呢？

## 涉及题目：
> [Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/#/description)  
> [Next Greater Element II](https://leetcode.com/problems/next-greater-element-ii/#/description)  
> [Basic Calculator](https://leetcode.com/problems/basic-calculator/#/description)  
> [Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/#/description)  
> [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/#/description)

## 思路分析

### Next Greater Element I
题目要求虽然比较长，但要求我们做的事其实并不复杂，即在一个数组中，找出每个数的右边第一个比他大的数。如果没有就记为-1。也就是说，当这个数是最后一个数或者
他后面没有比他大的数时，就输出-1。所以我们需要的是这样一种数据结构：
+ 它可以帮助我们排序，因为我们需要找较大的数  
+ 它支持对一侧操作，不会影响另一侧已经操作过的元素

到此，我们可以猜测可能需要stack来辅助，因为stack可以**保持其中数据增序或者降序排列**，则下一步要考虑的是：**什么样的数据应该留在栈底？**   
假设原数组内元素是降序排列的，每个元素后面都不会有比它大的数，返回的结果将全是-1.往往最后这些处理不了的结果是留在栈中的，那么我们首先应该考虑让栈维持从
栈底到栈顶递减。  

假设原数组为[5,3,2,4,1],元素依次进栈，新进元素小于栈顶元素就直接置于栈顶，否则将栈顶挤出，知道栈顶更大或者栈为空。从下图可以看出，5，3，2进栈时依次
放入栈顶，当4进栈时，由于3，2均小于4，4依次挤出3和2，后被放在5之上，成为新的栈顶。而我们同时可以得出：**4就是3和2右边第一个大于他们的结果**。此后
2也被挤入栈中。数组遍历结束，栈中最后剩余的5，4和2即说明他们的后面都没有比他们大的数，对应的结果将是-1.
![](https://github.com/zhaoxy136/LeetCode/blob/master/Summary%20and%20Tricky%20tips/assets/Stack%201.1.png)

对这道题，我们还需要用hashmap记录每个元素对应的next greater element，代码实现如下：

    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            while (!stack.isEmpty() && stack.peek() < n) {
                map.put(stack.pop(), n);
            }
            stack.push(n);
        }
        int[] res = new int[findNums.length];
        for (int i = 0; i < findNums.length; i++) {
            res[i] = map.getOrDefault(findNums[i], -1);
        }
        return res;
    }

当然此题也可以将数组从后向前遍历，思路为找到每一个元素从最右边向左最后一个比自己大的元素。  
栈中依然是保持降序排列。栈顶留下的元素即为从右向左最后一个比自己大的，如果栈为空，说明自己是从本身向后最大的元素。然后再把自己压入栈内。部分代码如下：

    for (int i = nums.length-1; i >= 0; i--) {
        while (!stack.isEmpty() && nums[i] > stack.peek()) {
            stack.pop();
        }
        if (stack.isEmpty()) {
            map.put(nums[i], -1);
        } else {
            map.put(nums[i], stack.peek());
        }
        stack.push(nums[i]);
    }

以上两个思路是从不同角度来思考，但都用到了栈的特性：可以维持但个方向上的有序（但序列内元素可以不在栈内）
其实本题也可以通过binary search来解决，原因是我们要找到“每个元素右侧第一个大于自身的元素”，很符合二分法的基本描述。不过二分法的时间复杂度为O(nlogn)，
而用stack可以将其降低至O(n).

### Next Greater Element II
本题加入了一点改动，就是原数组改为了循环数组。即说明只有数组中最大的数没有结果，其他数都将有结果。  

**往往对于循环数组的问题，其实只要重复两边就可得出答案**

那么本题依然可以有两个角度来解决，如果从左到右,考虑右侧第一个大于自己的元素，则需要在一次遍历结束后再进行一次遍历，从而补全上一轮没有结果的元素。  
当然，如果从后向前考虑，则应找到从右向左最后一个大于自身的元素。而此处产生了区别，我们并不需要重复两次操作，只需要先将数组内元素从后向前依次压入栈内，
再进行从后向前的遍历，则每遍历到一个点便一定会有答案，除非自身就是数组中最大的数。而这样的话，再向前的数也至少会有答案。

所以对于这道题，显然从后向前遍历更好：

    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length-1; i>= 0; i--) {
            stack.push(nums[i]);
        }
        for (int i = nums.length-1; i>= 0; i--) {
            while (!stack.isEmpty() && nums[i] >= stack.peek()) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return res;
    }

### Basic Calculator I
计算器的题目好似自然就与stack有关联，因为**算式内包含着取多嵌套关系，每一层内又是一个算式；这一层结束后将结果返回给上一层继续运算。**

那么我们要关注的点就在于什么时候进行进栈操作，什么时候进行出栈操作。对于本题不涉及乘除操作，而只是加减和括号之间的操作。  
> 括号内的优先级高于括号外，就好比执行程序的过程中遇到了方法的调用；  
> 遇到开括号则说明要将上一层的运算暂停，进行下一层的运算，此时的状态就如同刚开始一样；  
> 遇到闭括号则说明内层运算结束，要将内层的结果返回给外层运算，并且继续外层运算的执行。

了解这些`机制`后，只要处理如何提取整数，如何设定符号，如何处理空格即可。关于用几个栈的问题，比较明朗的是采用两个栈的模式，一个用来存结果，另一个用来存
符号。  
但这道题涉及到的运算符号只有加减，我们可以把加减用1和-1代替，而且既然两个栈都是同时push和同时pop的。我们便可以用一个栈来储存这些信息，只要记得顺利就好。部分代码如下：

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int sign = 1;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int num = s.charAt(i) - '0';
                while (i+1 < s.length() && Character.isDigit(s.charAt(i+1))) {
                    num = num * 10 + s.charAt(i+1) - '0';
                    i++;
                }
                res += sign * num;
            } else if (s.charAt(i) == '+') {
                sign = 1;
            } else if (s.charAt(i) == '-') {
                sign = -1;
            } else if (s.charAt(i) == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                sign = stack.pop();
                res = res * sign + stack.pop();
            }
        }
        return res;
    }

### Basic Calculator II
本题去除了括号，而加入了乘除操作。所以还是有两个不同级别的优先级。  
所以按照之前的思路研究，什么时候入栈和什么时候出栈是这道题的关键。由于没有了开闭括号，我们并没有明确的内层操作开始和结束的标志。  
与上一题相同的是，我们需要记录的是当前的数值和符号，而且注意符号是指**值前面的符号**---这就是为什么我们会把sign初试为'+'的原因。  
那我们区分现在处于外层运算（加减）还是内层运算（乘除）的根据就是符号。如果符号是+或-，即可把当前的值压入栈内；若符号是\*或／，则需要将当前值与上一个值相互操作，需要挤出栈顶元素，操作后将新的值在放入栈顶。最后把栈内的元素全部相加即可得到结果。  
所以**每当遇到符号时都要考虑进栈和出栈**，此外，遍历进行到最后时也应该考虑进栈，否则最后一个值可能被忽略。

代码如下：

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num = num * 10 + s.charAt(i) - '0';
            }
            if ((s.charAt(i) != ' ' && (s.charAt(i) < '0' || s.charAt(i) > '9')) || i == s.length()-1) {
                if (sign == '+') {
                    stack.push(num);
                }
                if (sign == '-') {
                    stack.push(-1 * num);
                }
                if (sign == '*') {
                    stack.push(stack.pop() * num);
                }
                if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

### Largest Rectangle in Histogram
本题描述很简单，判定标准也比较清晰。类似木桶原理，矩形的宽即左右边界之差，矩形的高即为最矮的‘木板’高度。我们很容易想到O(n2)的解法：

    public int largestRectangleArea(int[] heights) {
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            int minHeight = heights[i];
            for (int j = i; j < heights.length; j++) {
                if (heights[j] < minHeight) {
                    minHeight = heights[j];
                }
                res = Math.max(res, (j - i + 1) * minHeight);
            }
        }
        return res;
    }
    
而当题目提出更优的时间复杂度需求时，我们可选的基本只有O(nlogn)和O(n)。往往logn要结合二分法或者其他数据结构来解决，显然此题不太适合。所以我们要向O(n)的方向取优化。 
过程中肯定会考虑DP的算法，但是DP擅长的往往是将非多项式级别的复杂度降低至多项式级别。从O(n2)向O(n)优化往往不是DP擅长的。

首先在思路上，依然是要遍历，我们这次选择遍历最短的板，考虑以此为高度的矩形最大面积是多少？显然我们需要找到其左右边界，即:
- 其左边第一个比它短的位置 + 1；
- 其右边第一个比他短的位置 - 1；

明白了我们的需求后再看这两句话的表述十分适合栈的发挥，由此我们尝试用栈来解决问题。接下来要考虑的是我们应该选用递增栈还是递减栈呢？  
因为我们要找到两侧第一个比自己短的位置，那么按照从左向右遍历来看，遇到大于栈顶元素时没有找到目标，遇到小于栈顶元素时即确定了栈顶右边第一个小于自己的位置。 ---- 所以我们要维持的是一个递增栈！以[2,1,5,6,2,3]为例
![](https://github.com/zhaoxy136/LeetCode/blob/master/Summary%20and%20Tricky%20tips/assets/increasing%20stack.png)

此题栈内存的应该是下标，因为需要计算坐标差来得出矩形宽度，为视觉方便，图中栈内存放的元素我们用数值来表示。  
从左至右依次遍历，首先2进栈，然后1进栈时由于1小于2，需要令2出栈。  
**此时我们得到以下信息：1是2右侧第一个比自己小的数，右边界为1.index - 1；2左侧第一个比自己小的数是此时栈内2之下的数，空即代表没有比自己小的数，左边界是0。  
**所以我们得到了**以2.index位置的值（并不能说明所有以2为高的矩形）为高的矩形最大面积是多少。**  

接着5，6依次进栈，栈内维持递增，至第二个2进栈时，首先要将6挤出栈，同时也确定了：以6的位置为高的矩形右边界是2.index-1，左边界是5.index+1。  
由于2<5，5也被挤出栈，确定对应矩形面积。最后2>1所以1不再出栈，将2进栈循环继续。  
当所有元素都进栈后，栈内依然存留1，2，3没有出栈。**没有出栈就没有得到以该元素位置为高的矩形面积**， 因此我们最后再将-1放入栈内，目的是为了将全部元素挤出。而-1对应的边界也很好处理，即为length-1. 代码部分如下：

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i <= heights.length; i++) {
            int val = i == heights.length ? -1 : heights[i];
            while (!stack.isEmpty() && val <= heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = i;
                if (!stack.isEmpty()) {
                    width -= stack.peek() + 1;
                }
                res = Math.max(res, height * width);
            }
            stack.push(i);
        }
        return res;
    }

## 小结
+ 凡事可以用递归解决的问题都可以用栈来处理，因为递归的本质也是用栈，只不过不需要自己维护而已。所以当遇到可以用递归思路，又很难实现的时候用一个自己维护的栈是一个不错的选择。
+ 栈作为一种线性的数据结构，可以为我们提供维持栈内顺序的作用，故可以根据需求分为`递增栈`和`递减栈`。
+ 使用栈的时候一定要分析清楚什么情况下进栈，什么情况下出栈。并且分析出进栈和出栈分别展现来哪些信息？（如：遇到了右边第一个比自己大／小的元素）
+ 此外还应注意栈内存的是什么？有时存的是数据本身，但对于数组有时存的又是index。


## 其他相关题目
> [Mini Parser](https://leetcode.com/problems/mini-parser/#/description)  
> [132 Pattern](https://leetcode.com/problems/132-pattern/#/description)  

