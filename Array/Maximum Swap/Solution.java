class Solution {
    public int maximumSwap(int num) {
        if (num < 10) return num;
        char[] str = String.valueOf(num).toCharArray();
        int i = 0;
        //62997
        while (i < str.length - 1 && str[i] >= str[i+1]) i++;
        if (i == str.length - 1) return num;
        int j = str.length - 1;
        int end = i;
        while (j > i) {
            if (str[j] > str[end]) {
                end = j;
            }
            j--;
        }
        int start = 0;
        while (start <= i && str[start] >= str[end]) start++;
        char c = str[start];
        str[start] = str[end];
        str[end] = c;
        return Integer.parseInt(new String(str));
    }
}
