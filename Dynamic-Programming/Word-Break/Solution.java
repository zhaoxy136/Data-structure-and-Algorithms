public class Solution {
    public int findMaxLength(Set<String> dict){
        int maxLength = 0;
        for(String word: dict){
            maxLength = Math.max(maxLength,word.length());
        }
        return maxLength;
    }
    
    
    public boolean wordBreak(String s, Set<String> wordDict) {
        int n = s.length();
        if(s == null || n == 0){//empty string
            return false;
        }
        int maxLength = findMaxLength(wordDict);      
        //state: f[i] = whether the frst i characters can be parted into dict pieces
        boolean[] f = new boolean[n+1];
        //initialize
        f[0] = true;
        //function f[i] = OR{f[j]}; j<i, j+1 to i is a word in the dict
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= maxLength && i-j>=0; j++){
                String word = s.substring(i-j,i);
                if(wordDict.contains(word) && f[i-j]){
                    f[i] = true;
                    break;
                }
            }
        }
        //answer
        return f[n];
        
    }
}
