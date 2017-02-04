//Version 0: 
//@copyright https://discuss.leetcode.com/topic/59314/java-solution
public class Solution {
    public int findNthDigit(int n) {
        int len = 1;
        int start = 1;
        long count = 9;
        while (n > len * count) {
            n -= len++ * count;
            start *= 10;
            count *= 10;
        }
        start += (n-1) / len;
        int bit = (n-1) % len;
        return Character.getNumericValue(Integer.toString(start).charAt(bit));
    }
}
