//Version 0: O(n3)

    public int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3) return 0;
        int res = 0;
        int[][] dp = new int[A.length][A.length];
        for (int i = 0; i < A.length - 2; i++) {
            for (int j = i + 1; j < A.length - 1; j++) {
                for (int k = j + 1; k < A.length; k++) {
                    if ((long)A[k] - A[j] == (long)A[j] - A[i]) {
                        res -= dp[j][k];
                        dp[j][k] += dp[i][j] + 1;
                        res += dp[j][k];
                    }
                }
            }
        }
        return res;
    }

//Version 1: O(n2)
//@copyright: https://mp.weixin.qq.com/s?__biz=MzA5MzE4MjgyMw==&mid=2649458042&idx=1&sn=d2760fd7726150bbdc90910399c77178&chksm=887eeb72bf09626425a40ab83905e860f7982137483523ee8b702a1ed17e9584029f682f892b&mpshare=1&scene=1&srcid=0921LIGRcBKRzosTuztKtttZ&key=65d8001a48e3b58b3a0ddbd68e8b6f90f8f717d9bb5f08940f2b068b55c0b9fd288f47fd8662e713acbbb54e6bd31f64b611327f6f1d49a2b47b3699f05e15c935673dfd8dda1bc439f9635e6d207dc5&ascene=0&uin=MTUyMzM1NTE2MQ%3D%3D&devicetype=iMac+MacBookAir7%2C2+OSX+OSX+10.12.2+build(16C67)&version=12020710&nettype=WIFI&fontScale=100&pass_ticket=VGZoTmUHxXYSVDzGJpt2BxihsgKjek1dcj20mGw5BVr7BI2DTunk8LwpAT84zayJ

class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3) return 0;
        int res = 0;
        Map<Integer, Integer>[] map = new Map[A.length];
        for (int i = 0; i < A.length; i++) {
            map[i] = new HashMap<>(i);
            for (int j = 0; j < i; j++) {
                long dif = (long)A[i] - A[j];
                if (dif <= Integer.MIN_VALUE || dif > Integer.MAX_VALUE) continue;
                int d = (int)dif;
                int c1 = map[i].getOrDefault(d, 0);
                int c2 = map[j].getOrDefault(d, 0);
                map[i].put(d, c1 + c2 + 1);
                res += c2;
            }
        }
        return res;
    }
}
