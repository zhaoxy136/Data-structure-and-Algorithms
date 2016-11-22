/**
  *a lower bound of Ω(n^ceil(k/2)) for the k-SUM problem (deciding whether k numbers out of n sums to 0) 
  *where ceil is the ceiling function. Here k=3, so you cannot do better than Ω(n^2).
  */
//Version 0: 
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            helper(res, nums, -nums[i], i+1);
        }
        return res;
    }
    
    private void helper(List<List<Integer>> res, int[] nums, int target, int start) {
        int low = start;
        int high = nums.length - 1;
        while (low < high) {
            if (nums[low] + nums[high] == target) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(-target);
                list.add(nums[low]);
                list.add(nums[high]);
                res.add(list);
                low++;
                high--;
                while (low < high && nums[low] == nums[low-1]) {
                    low++;
                }
                while (low < high && nums[high] == nums[high+1]) {
                    high--;
                }
            } else if (nums[low] + nums[high] < target) {
                low++;
            } else {
                high--;
            }
        }
    }
}

//Version 1: much more elegant
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length < 3) return result;
        Arrays.sort(nums);
        int i = 0;
        while(i < nums.length - 2) {
            if(nums[i] > 0) break;
            int j = i + 1;
            int k = nums.length - 1;
            while(j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if(sum == 0) result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                if(sum <= 0) while(nums[j] == nums[++j] && j < k);
                if(sum >= 0) while(nums[k--] == nums[k] && j < k);
            }
            while(nums[i] == nums[++i] && i < nums.length - 2);
        }
        return result;
    }
}
