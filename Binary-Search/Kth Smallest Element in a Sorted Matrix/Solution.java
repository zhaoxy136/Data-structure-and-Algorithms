//Version 0: binary search
public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        int start = matrix[0][0];
        int end = matrix[matrix.length-1][matrix[0].length-1];
        while (start < end) {
            int mid = start + (end - start) / 2;
            int count = check(matrix, mid);
            if (count < k) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
    private int check(int[][] matrix, int val) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            int j = matrix[i].length;
            while (--j >= 0 && matrix[i][j] > val);
            count += j+1;
        }
        return count;
    }
}

//Version 1: priority queue
//just like dealing with k sorted array

