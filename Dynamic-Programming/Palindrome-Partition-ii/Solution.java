//Version 0: 
public class Solution {
    public int minCut(String s) {
        int n = s.length();
        //preparation
        boolean isPalindrome[][] = getIsPalindrome(s);
        //state: f[i] = the min cuts for the first i characters in the string
        int[] f = new int[n + 1];
        
        //initialize
        for(int i = 0; i <= n; i++){
            f[i] = i - 1;
        }
        //function
        for(int i = 2; i <= n; i++){
            for(int j = 0; j < i; j++){
                if(isPalindrome[j][i-1]){
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
        }
        
        //answer f[n];
        return f[n];
    }
    
    public boolean[][] getIsPalindrome(String s){
        int n = s.length();
        //state: f[i][j] = whether the substring from the ith to jth character is pallindrome
        boolean[][] f = new boolean[n][n];
        //initialize
        for(int i = 0; i < n; i++){
            f[i][i] = true;
        }
        for(int i = 0; i < n - 1; i++){
            if(s.charAt(i) == s.charAt(i + 1)){
                f[i][i + 1] = true;
            }
        }
        //function
        for(int length = 2; length < n; length++){
            for(int start = 0; start + length < n; start++){
                f[start][start + length] = f[start + 1][start + length - 1] 
                    && (s.charAt(start) == s.charAt(start + length));
            }
        }
        //answer
        return f;
    }
    
/*    
    public boolean isPalindrome(String s, int start, int end){
        for(int i = start, j = end; i < j; i++, j--){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
        }
        return true;
    }
*/    
}

//Version 1:(copyright @ tqlong)
public class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] f = new int[n+1];
        //initialize
        for(int i = 0; i <= n; i++){
            f[i] = i - 1;
        }
        //DP
        for(int i = 0; i < n; i++){
            for(int j = 0; i-j>=0 && i+j<n && s.charAt(i-j) == s.charAt(i+j); j++){// odd length palindrome
                f[i+j+1] = Math.min(f[i+j+1], f[i-j] + 1);
            }
            
            for(int j = 1; i-j+1>=0 && i+j<n && s.charAt(i-j+1) == s.charAt(i+j); j++){//even length palindrome
                f[i+j+1] = Math.min(f[i+j+1], f[i-j+1] + 1);
            }
            
        }
        //answer
        return f[n];
    }
}
