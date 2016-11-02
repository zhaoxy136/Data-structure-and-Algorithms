//Version: loop
public class Solution {
    public boolean isPowerOfFour(int num) {
        if(num == 1){
            return true;
        }
        int temp = 1;
        for(int i = 0; i < 15; i++){
            temp *= 4;
            if(temp == num){
                return true;
            }
        }
        return false;
    }
}

//Version 1: 
//0x55555555 is to get rid of those power of 2 but not power of 4
//so that the single 1 bit always appears at the odd position 
public class Solution {
    public boolean isPowerOfFour(int num) {
        return (num & (num-1)) == 0 && (num & 0x55555555) != 0;
    }
}
