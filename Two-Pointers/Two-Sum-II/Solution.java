//Version 0: 
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return null;
        }
        int[] res = new int[2];
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            if (numbers[low] + numbers[high] == target) {
                return new int[]{low+1, high+1};
            }
            if (numbers[low] + numbers[high] < target) {
                low++;
            } else {
                high--;
            }
        }
        return res;
    }
}
