//Version 0:
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length < nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int m = nums1.length, n = nums2.length;
        int start = (m-n)/2, end = start + n;
        int rightMin = 0, leftMax = 0;
        while (start <= end) {
            int i = start + (end - start) / 2;
            int j = (m + n) / 2 - i;
            if (j < n && nums1[i-1] > nums2[j]) {
                end = i - 1;
            } else if (j > 0 && nums2[j-1] > nums1[i]) {
                start = i + 1;
            } else {
                if (i == m) {
                    rightMin = nums2[j];
                } else if (j == n) {
                    rightMin = nums1[i];
                } else {
                    rightMin = Math.min(nums1[i], nums2[j]);
                }
                if ((m+n) % 2 == 1) return rightMin;
                if (i == 0) {
                    leftMax = nums2[j-1];
                } else if (j == 0) {
                    leftMax = nums1[i-1];
                } else {
                    leftMax = Math.max(nums1[i-1], nums2[j-1]);
                }
                return (leftMax + rightMin) / 2.0;
            }  
        }
        return 0.0;
    }
}

//Version 1:
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        if (len % 2 == 0) {
            return (findKth(nums1, nums2, 0, 0, len/2) + findKth(nums1, nums2, 0, 0, len/2 + 1))/2.0;
        }
        return findKth(nums1, nums2, 0, 0, len/2 + 1) / 1.0;
    }
    private int findKth(int[] nums1, int[] nums2, int startA, int startB, int k) {
        if (startA >= nums1.length) {
            return nums2[startB+k-1];
        } else if (startB >= nums2.length) {
            return nums1[startA+k-1];
        } else if (k == 1){
            return Math.min(nums1[startA], nums2[startB]);
        }
        if (startA + k/2 - 1 >= nums1.length) {
            startB += k/2;
        } else if (startB + k/2 - 1 >= nums2.length) {
            startA += k/2;
        } else if (nums1[startA + k/2 - 1] < nums2[startB + k/2 - 1]){
            startA += k/2;
        } else {
            startB += k/2;
        }
        return findKth(nums1, nums2, startA, startB, k - k/2);
    }
}
