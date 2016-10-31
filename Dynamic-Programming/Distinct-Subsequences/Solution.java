//Version 0: 
public class Solution {
    public int numDistinct(String s, String t) {
        if(s == null || s.length() == 0){
            return 0;
        }
        if(t == null || t.length() == 0){
            return 0;
        }
        //state: f[i][j]: number of first i characters of T in first j characters of S
        int[][] f = new int[t.length()+1][s.length()+1];
        //initalize
        for(int i = 0; i <= s.length(); i++){
            f[0][i] = 1;
        }
        for(int i = 1; i <= t.length(); i++){
            f[i][0] = 0;
        }
        
        //function: f[i][j] = (s.j-1 != t.i-1)? f[i][j-1] :(f[i-1][j-1] + f[i][j-1])
        for(int j = 1; j <= s.length(); j++){
            for(int i = 1; i <= t.length() && i <= j; i++){
                f[i][j] = (s.charAt(j-1) != t.charAt(i-1))? f[i][j-1] :(f[i-1][j-1] + f[i][j-1]);
            }
        }
        //answer
        return f[t.length()][s.length()];
    }
}

