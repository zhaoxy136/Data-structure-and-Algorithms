//Version 0: (17 ms)
public class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int tmp = map.getOrDefault(nums[i], 0);
            if (tmp == 1){
                return true;
            }
            map.put(nums[i], tmp+1);
        }
        return false;
    }
}

//Version 1: (16 ms)
public class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return true;
            }
            map.put(nums[i], 1);
        }
        return false;
    }
}

//Version 2: (14 ms)
public class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }
}

//Version 3: (7 ms) but why O(nlogn) is faster than O(n)?
public class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums.length <= 1) {
            return false;
        }

        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++){
            if (nums[i] == nums[i-1]){
                return true;
            }
        }
        return false;
    }
}

//Version 4: (4 ms) use two pointers
public class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums.length <= 1) {
            return false;
        }
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length -1;
        while (i <= j) {
            if (nums[i] == nums[i+1]) {
                return true;
            }            
            if (nums[j] == nums[j-1]) {
                return true;
            }
            i++;
            j--;
        }
        return false;
    }
}
