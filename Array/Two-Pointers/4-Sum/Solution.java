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

//Version 1: throws some impossible cases
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        if (n < 4) {
            return res;
        }
        Arrays.sort(nums);
        int i = 0;
        while (i < nums.length - 3) {
            if (4 * nums[i] > target) break;
            int j = i + 1;
            while (j < nums.length - 2) {
                if (3 * nums[j] > target - nums[i]) break;
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

//Version 2: throws more impossible cases @copyright fei38
public List<List<Integer>> fourSum2(int[] nums, int target) {
	if (nums == null || nums.length < 4)
		return res;
	ArrayList<List<Integer>> res = new ArrayList<>();
	Arrays.sort(nums);

	if (nums[0] * 4 > target || nums[nums.length - 1] * 4 < target) { 
		return res;
	}
	
	//following is four sum:
	for (int i = 0; i < nums.length - 3; i++) {
		if (nums[i] + nums[nums.length - 1] * 3 < target) //nums[i] is too small
			continue;
		if (nums[i] * 4 > target) { //nums[i] is too big
			break;
		}

		if (i > 0 && nums[i] == nums[i - 1]) //jump duplicate
			continue;
		
		//following is three sum:
		for (int j = i + 1; j < nums.length - 2; j++) {
			if (nums[i] + nums[j] + nums[nums.length - 1] * 2 < target) //nums[j] is too small
				continue;
			if (nums[i] + nums[j] * 3 > target) { //nums[j] is too big
				break;
			}

			if (j > i + 1 && nums[j] == nums[j - 1]) //jump duplicate
				continue;
			
			//following is two sum:
			int low = j + 1, high = nums.length - 1;
			while (low < high) {
				int sum = nums[i] + nums[j] + nums[low] + nums[high];
				if (sum == target) {
					res.add(Arrays.asList(nums[i], nums[j], nums[low],
							nums[high]));
					while (low < high && nums[low] == nums[low + 1]) //jump duplicate
						low++;
					while (low < high && nums[high] == nums[high - 1]) //jump duplicate
						high--;
					low++;
					high--;
				} else if (sum < target)
					low++;
				else
					high--;
			}
		}
	}
	return res;
}
