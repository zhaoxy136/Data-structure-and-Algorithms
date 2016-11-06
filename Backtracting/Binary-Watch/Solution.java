
public class Solution {
    public List<String> readBinaryWatch(int num) {
        List<String> time = new ArrayList<>();
        int[] hours = new int[]{8, 4, 2, 1};
        int[] mins = new int[]{32, 16, 8, 4, 2, 1};
        for(int i = 0; i <= num; i++){
            List<Integer> list1 = generateTime(hours, i);
            List<Integer> list2 = generateTime(mins, num-i);
        for(int hour : list1){
            if(hour >= 12){
                continue;
            }
            for(int minute : list2){
                if(minute >= 60){
                    continue;
                }
                if(minute < 10){
                    time.add(hour + ":0" + minute);
                }else{
                    time.add(hour + ":" + minute);
                }
            }
        }
        }
        return time;
    }
    
    private List<Integer> generateTime(int[] nums, int count){
        List<Integer> res = new ArrayList<>();
        helper(res, nums, 0, 0, count);
        return res;
    }
    
    private void helper(List<Integer> res, int[] nums, int position, int sum, int count) {
        if(count == 0){
            res.add(sum);
            return;
        }
        for(int i = position; i < nums.length ; i++){
            sum += nums[i];
            helper(res, nums, i+1, sum, count-1);
            sum -= nums[i];
        }
    }
}
