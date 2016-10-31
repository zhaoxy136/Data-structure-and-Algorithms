//Version 0: 
public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if(A == null || A.length < 3){
            return 0;
        }
        //preparation
        int[] subNum = helper(A);
        //state: f[i]:number of slices within first i elements
        int[] f = new int[A.length+1];
        //initialize
        f[0] = 0;
        f[1] = 0;
        f[2] = 0;
        //function
        for(int i = 3; i <= A.length; i++){
            if(A[i-1] - A[i-2] != A[i-2] - A[i-3]){
                f[i] = f[i-1];
            }else{
                f[i] = f[i-1] + subNum[i-1];
            }
        }
        //answer
        return f[A.length];
    }
    
    public int[] helper(int[] A){
        //this array to store the number of subarrays which end with A[i]
        int[] subNum = new int[A.length];
        int step = A[1] - A[0];
        int length = 2;
        for(int i = 2; i < A.length; i++){
            int temp = A[i] - A[i-1];
            if(step == temp){
                length++;
            }else{
                length = 2;
            }
            //length = (step == temp) ? length++ : 2;
            step = temp;
            subNum[i] = length - 2;
        }
        return subNum;
    }
}

//Version 1:
public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int curr = 0, sum = 0;
    for (int i=2; i<A.length; i++)
        if (A[i]-A[i-1] == A[i-1]-A[i-2]) {
            curr += 1;
            sum += curr;
        } else {
            curr = 0;
        }
    return sum;
    }
}

