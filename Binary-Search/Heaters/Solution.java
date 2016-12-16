//Version 0: binary search
public class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int radius = 0;
        for (int pos : houses) {
            int min = binarySearch(heaters, pos);
            radius = Math.max(radius, min);
        }
        return radius;
    }
    private int binarySearch(int[] nums, int n) {
        if (nums == null || nums.length == 0) {
            return Integer.MAX_VALUE;
        }
        int start = 0;
        int end = nums.length-1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == n) return 0;
            if (nums[mid] < n) start = mid;
            else end = mid;
        }
        if (nums[end] < n) return n-nums[end];
        if (nums[start] > n) return nums[start] - n;
        return Math.min(n-nums[start], nums[end]-n);
    }
}

//Version 1: simply use Arrays.binarySearch()
// @copyright https://discuss.leetcode.com/topic/71460/short-and-clean-java-binary-search-solution
public class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int result = Integer.MIN_VALUE;
        
        for (int house : houses) {
        	int index = Arrays.binarySearch(heaters, house);
        	if (index < 0) {
        		index = -(index + 1);
        	}
        	int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
        	int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;
        	
        	result = Math.max(result, Math.min(dist1, dist2));
        }
        
        return result;
    }
}

//Version 2: two pointers (I thinks it's best)
// @copyright https://discuss.leetcode.com/topic/71450/simple-java-solution-with-2-pointers
public class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int radius = 0;
        int i = 0;
        for (int house : houses) {
            while (i < heaters.length-1 && heaters[i] + heaters[i+1] <= 2 * house) {
                i++;
            }
            radius = Math.max(radius, Math.abs(heaters[i]-house));
        }
        return radius;
    }
}

//Version 3: TreeSet (interesting)
//@copyright https://discuss.leetcode.com/topic/71549/treeset-is-a-good-thing-i-love-it
public class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        TreeSet<Integer> treeSet=new TreeSet<>();
        for (int h: heaters) treeSet.add(h);
        int max=0;
        for (int h: houses) {
            Integer l=treeSet.floor(h), r=treeSet.higher(h);
            max=Math.max(max, Math.min(l==null?Integer.MAX_VALUE:h-l, r==null?Integer.MAX_VALUE:r-h));
        }
        return max;
    }
}
