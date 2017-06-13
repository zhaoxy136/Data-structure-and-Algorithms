//Version 0:
public class Solution {
    class ResultType {
        float max, min;
        String max_str, min_str;
        ResultType (float max, float min, String max_str, String min_str){
            this.max = max;
            this.min = min;
            this.max_str = max_str;
            this.min_str = min_str;
        }
    }
    public String optimalDivision(int[] nums) {
        return helper(nums, 0, nums.length-1).max_str;
    }
    private ResultType helper(int[] nums, int start, int end) {
        float max = Float.MIN_VALUE;
        float min = Float.MAX_VALUE;
        String max_str = "", min_str = "";
        if (start == end) {
            max = min = (float)nums[start];
            max_str = min_str = "" + nums[start];
        } else if (start + 1 == end) {
            max = min = (float)nums[start] / nums[end];
            max_str = min_str = "" + nums[start] + "/" + nums[end];
        } else {
            for (int i = start; i < end; i++) {
                ResultType left = helper(nums, start, i);
                ResultType right = helper(nums, i+1, end);
                if (max < left.max / right.min) {
                    max = left.max / right.min;
                    max_str = left.max_str + "/" + (i+1 == end ? "" : "(") + right.min_str + (i+1 == end ? "" : ")");
                }
                if (min > left.min / right.max) {
                    min = left.min / right.max;
                    min_str = left.min_str + "/" + (i+1 == end ? "" : "(") + right.max_str + (i+1 == end ? "" : ")");
                }
                
            }
        }
        
        return new ResultType(max, min, max_str, min_str);
    }
}

//Version 1:
public class Solution {
    class ResultType {
        float max, min;
        String max_str, min_str;
        ResultType (float max, float min, String max_str, String min_str){
            this.max = max;
            this.min = min;
            this.max_str = max_str;
            this.min_str = min_str;
        }
    }
    public String optimalDivision(int[] nums) {
        ResultType[][] memo = new ResultType[nums.length][nums.length];
        helper(nums, 0, nums.length-1, memo);
        return memo[0][nums.length-1].max_str;
    }
    private void helper(int[] nums, int start, int end, ResultType[][] memo) {
        if (memo[start][end] != null) return;
        float max = Float.MIN_VALUE;
        float min = Float.MAX_VALUE;
        String max_str = "", min_str = "";
        if (start == end) {
            max = min = (float)nums[start];
            max_str = min_str = "" + nums[start];
            memo[start][end] = new ResultType(max, min, max_str, min_str);
            return;
        }
        for (int i = start; i < end; i++) {
            helper(nums, start, i, memo);
            helper(nums, i+1, end, memo);
            if (max < memo[start][i].max / memo[i+1][end].min) {
                max = memo[start][i].max / memo[i+1][end].min;
                max_str = memo[start][i].max_str + "/" + (i+1 == end ? "" : "(") + memo[i+1][end].min_str + (i+1 == end ? "" : ")");
            }
            if (min > memo[start][i].min / memo[i+1][end].max) {
                min = memo[start][i].min / memo[i+1][end].max;
                min_str = memo[start][i].min_str + "/" + (i+1 == end ? "" : "(") + memo[i+1][end].max_str + (i+1 == end ? "" : ")");
            }
        }
        memo[start][end] = new ResultType(max, min, max_str, min_str);
    }
}



