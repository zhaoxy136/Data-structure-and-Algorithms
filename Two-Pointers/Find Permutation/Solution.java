//Version 0: using stack
class Solution {
    public int[] findPermutation(String s) {
        int[] res = new int[s.length() + 1];
        Stack<Integer> st = new Stack<>();
        int start = 0;
        for (int i = 0; i <= s.length(); i++) {
            if (i == s.length() || s.charAt(i) == 'I') {
                st.push(i+1);
                while (!st.isEmpty()) {
                    res[start++] = st.pop();
                }
            } else {
                st.push(i+1);
            }
        }
        return res;
    }
}

//Version 1: two pointers
class Solution {
    public int[] findPermutation(String s) {
        int[] res = new int[s.length() + 1];
        int i = 0, j = 0;
        while (i <= s.length()) {
            if (i == s.length() || s.charAt(i) == 'I') {
                int k = i + 1;
                while (j <= i) {
                    res[j++] = k--;
                }
            }
            i++;
        }
        return res;
    }
}
