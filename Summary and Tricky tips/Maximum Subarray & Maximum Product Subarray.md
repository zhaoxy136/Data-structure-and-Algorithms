## 涉及题目
[Maximum subarray:](https://leetcode.com/problems/maximum-subarray/#/description)  
[Maximum product subarray:] (https://leetcode.com/problems/maximum-product-subarray/#/description)

这两道题都用了DP的思想，但又进行了极大简化。 第一题如果从DP的角度看，应该用一个array来记录 dp[i]表示，以i位置元素结尾的最大和subarray的和。  
则有dp[i] = dp[i-1] + arr[i-1] 和 arr[i-1]本身组成的array相比较。  
最后从dp[i]中选择最大的结果作为最终结果。  
但由于dp[i]仅和dp[i-1]有关， 我们便用一个数来记录此值，并不断根据其来更新result，待遍历结束返回result即可！ 非常巧妙！

第二题更是进一步升级。只不过同时用max 和 min来记录最大和最小的subarray乘积， 并且当nums[i] 为负数时，将max和min互相来做更新就好了～ 实在巧妙，
又不失DP的精髓！
