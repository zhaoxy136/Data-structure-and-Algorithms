public class Solution {
    /**
     * 2 -> 1*1 = 1
     * 3 -> 2*1 = 2
     * 4 -> 2*2 = 4
     * 5 -> 2*3 = 6
     * 6 -> 3*3 = 9
     * ----------------
     * 7 -> 2*2*3 = f[4] * 3 = 12
     * 8 -> f[5] * 3 = 18
     * f[i] = f[i-3] * 3;
     */
    
    public int integerBreak(int n) {
        if(n == 2){
            return 1;
        }
        if(n == 3){
            return 2;
        }
        if(n % 3 == 0){
            return (int)Math.pow(3,n/3);
        }
        if(n % 3 == 1){
            return ((int)Math.pow(3,n/3))* 4/3;
        }
        return ((int)Math.pow(3,n/3)) * 2;
    }
}
