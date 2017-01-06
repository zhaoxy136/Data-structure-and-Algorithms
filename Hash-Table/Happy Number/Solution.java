//Version 0:
public class Solution {
    public boolean isHappy(int n) {
        if (n <= 0) return false;
        Set<Integer> set = new HashSet<>();
        while (!set.contains(1)) {
            int tmp = 0;
            while (n > 9) {
                tmp += (n%10) * (n%10);
                n /= 10;
            }
            n = n * n + tmp;
            if (set.contains(n)) return false;
            set.add(n);
        }
        return true;
    }
}

//Version 1: so smart O(1) space
//@copyright: https://discuss.leetcode.com/topic/12587/my-solution-in-c-o-1-space-and-no-magic-math-property-involved/12
public class Solution {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = helper(n);
        while (slow != fast) {
            slow = helper(slow);
            fast = helper(fast);
            fast = helper(fast);
        }
        if (slow == 1) return true;
        return false;
    }
    private int helper(int n) {
        int res = 0;
        while (n > 9) {
            res += (n%10) * (n%10);
            n /= 10;
        }
        return res + n * n;
    }
}
