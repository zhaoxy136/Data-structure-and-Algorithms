//Version 0: same as 3 sum(90+ ms)
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        if (n < 4) {
            return res;
        }
        Arrays.sort(nums);
        //int sum = 0;
        int i = 0;
        //int j, start, end, sum;
        while (i < nums.length - 3) {
        //for (int i = 0; i < nums.length - 3; i++) {
            //if (i > 0 && nums[i] == nums[i-1]) {
            //    continue;
            //}
            //if (nums[i] > target) break;
            int j = i + 1;
            while (j < nums.length - 2) {
            //for (int j = i+1; j < nums.length - 2; j++) {
                //if (j > i+1 && nums[j] == nums[j-1]) {
                //    continue;
                //}
                //if (nums[i] + nums[j] > target) break;
                int start = j + 1;
                int end = nums.length - 1;
                while (start < end) {
                    int sum = nums[i] + nums[j] + nums[start] + nums[end];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[start], nums[end]));
                    }
                    if (sum <= target) {
                        while (nums[start] == nums[++start] && start < end);
                    } 
                    if (sum >= target) {
                        while (nums[end--] == nums[end] && start < end);
                    }
                }
                while (nums[j] == nums[++j] && j < nums.length - 2);
            }
            while (nums[i] == nums[++i] && i < nums.length - 3);
        }
        return res;
    }
}

//
