class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int[] counts = new int[s2.length() + 1];
        int[] indice = new int[s2.length() + 1];
        int count = 0, index = 0;
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < s1.length(); j++) {
                if (s1.charAt(j) == s2.charAt(index)) {
                    index++;
                }
                if (index == s2.length()) {
                    count++;
                    index = 0;
                }
            }
            counts[i] = count;
            indice[i] = index;
            for (int k = 0; k < i; k++) {
                if (indice[k] == index) {
                    int preCount = counts[k];
                    int loopCount = (counts[i] - counts[k]) * ((n1 - k - 1) / (i - k));
                    int remainCount = counts[k+(n1-k-1) % (i-k)] - counts[k];
                    return (preCount + loopCount + remainCount) / n2;
                }
            }
        }
        return count / n2;
    }
}
