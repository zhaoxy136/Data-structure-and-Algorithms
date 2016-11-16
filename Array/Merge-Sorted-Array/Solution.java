public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        int i = m - 1;
        int j = n - 1;
        while (i > -1 && j > -1){
            if (nums1[i] < nums2[j]){
                nums1[index--] = nums2[j--];
            } else {
                nums1[index--] = nums1[i--];
            }
        }
        if (j >= 0){
            for (int k = 0; k <= j; k++){
                nums1[k] = nums2[k];
            }
        }
        /*
        while (j > -1){
            nums1[index--] = nums2[j--];
        }
        */
    }
}
