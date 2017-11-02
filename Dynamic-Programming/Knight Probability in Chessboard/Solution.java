class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        if (K == 0) return 1d;
        double[][] prev = new double[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(prev[i], 1);
        }
        double[][] cur = new double[N][N];;
        int[] dir = {-2, -1, 2, 1, -2, 1, 2, -1, -2};
        for (int k = 1; k <= K; k++) {
            cur = new double[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int d = 0; d < 8; d++) {
                        int i_n = i + dir[d];
                        int j_n = j + dir[d+1];
                        if (i_n >= 0 && i_n < N && j_n >= 0 && j_n < N) cur[i][j] += prev[i_n][j_n];
                    }
                }
            }
            prev = cur;
        }
       return cur[r][c] / Math.pow(8, K);
    }
}
