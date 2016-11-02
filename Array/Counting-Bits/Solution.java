//Version 0: original
public class Solution {
    public int[] countBits(int num) {
        int[] result = new int[num+1];
        result[0] = 0;
        if(num == 0){
            return result;
        }
        result[1] = 1;
        if(num == 1){
            return result;
        }
        int index = 2;
        int step = 2;
        while(index <= num){
            for(int j = 0; j < step; j++){
                result[index] = result[index-step] + 1;
                if(index == num){
                    return result;
                }else{
                    index++;
                }
            }
            step *= 2;
        }
        return result;
    }
}

//Version 1: 
/**
 *The fact that any even number has the same number of set bits as in its half,
 *and odd numbers have 1 extra set bit than its predecessor.
 *i.e: f[i] = f[i/2] + (i%2)
 */
public class Solution {
    public int[] countBits(int num) {
        int[] result = new int[num+1];
        result[0] = 0;
        for(int i = 1; i <= num;i++){
            result[i] = result[i >>> 1] + (i & 1);
        }
        return result;
    }
}
