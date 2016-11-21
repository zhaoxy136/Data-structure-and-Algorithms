//Version 0: 
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix[0] == null) {
            return false;
        }
        int start = 0;
        int end = matrix.length - 1;
        int mid;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (matrix[mid][0] == target) {
                return true;
            }
            if (matrix[mid][0] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        int row;
        if (matrix[end][0] <= target) {
            row = end;
        } else if (matrix[start][0] <= target) {
            row = start;
        } else {
            return false;
        }
        for (int i = 0; i <= row; i++) {
            start = 0;
            end = matrix[i].length - 1;
            while (start + 1 < end) {
                mid = start + (end - start) / 2;
                if (matrix[i][mid] == target) {
                    return true;
                }
                if (matrix[i][mid] < target) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
            if (matrix[i][start] == target || matrix[i][end] == target) {
                return true;
            }
        }
        return false;
    }
}

//Version 1:
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix[0] == null) {
            return false;
        }
        int row = matrix.length - 1;
        int col = 0;
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] == target) {
                return true;
            }
            if (matrix[row][col] < target) {
                col++;
            } else {
                row--;
            }
        }
        return false;
    }
}
