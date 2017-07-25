//Version 0: TLE
public class Solution {
    public int numSquares(int n) {
        if (n < 1) return 0;
        int[] f = new int[n+1];
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            if (isSquare(i)) {
                f[i] = 1;
                continue;
            }
            f[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= i/2; j++) {
                f[i] = Math.min(f[i], f[j] + f[i-j]);
            }
        }
        return f[n];
    }
    private boolean isSquare(int n) {
        for (int i = 1; i * i <= n; i++) {
            if (i*i == n) return true;
        }
        return false;
    }
}

//Version 1: 
// To get the value of dp[n], we should choose the min
// value from:
//     dp[n - 1] + 1,
//     dp[n - 4] + 1,
//     dp[n - 9] + 1,
//     dp[n - 16] + 1
//     and so on...
public class Solution {
    public int numSquares(int n) {
        if (n < 1) return 0;
        int[] f = new int[n+1];
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            if (isSquare(i)) {
                f[i] = 1;
                continue;
            }
            f[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                f[i] = Math.min(f[i], f[i-j*j] + 1);
            }
        }
        return f[n];
    }
    private boolean isSquare(int n) {
        for (int i = 1; i * i <= n; i++) {
            if (i*i == n) return true;
        }
        return false;
    }
}

//Version 2: much more elegant!!
public class Solution {
    public int numSquares(int n) {
        int[] f = new int[n+1];
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                f[i] = Math.min(f[i], f[i-j*j] + 1);
            }
        }
        return f[n];
    }
}

//BFS method
public class Solution {
    public int numSquares(int n) {
        if (n <= 0) return 0;
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> squares = new ArrayList<>();
        int[] nums = new int[n+1];
        for (int i = 1; i * i <= n; i++) {
            if (i * i == n) return 1;
            queue.offer(i*i);
            squares.add(i*i);
            nums[i*i] = 1;
        }
        int step = 1;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            while (size-- > 0) {
                int val = queue.poll();
                for (int num : squares) {
                    if (val + num == n) return step;
                    if (val + num < n && nums[val + num] == 0) {
                        queue.offer(val + num);
                        nums[val+num] = step;
                    }
                }
            }
        }
        return 0;
    }
}
