import java.util.Arrays;

class Solution {
    /**
     * num1 m   实际长度大于等于m+n
     * num2 n
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        //将nums1中所有元素往后搬n个位置
        move(nums1, m, n);

        //从 nums1的 n的索引位置, 到 n+m-1的位置, 以及从 nums2 0-n-1的位置, 合并到 nums1中去即可

        //合并之后nums1元素的起始索引
        int n1 = n;

        //合并之后nums2元素的起始索引
        int n2 = 0;

        int count = 0;

        while (n1 <= n + m - 1 && n2 <= n - 1) {
            //谁小, 谁先放入

            //nums1的小, 就放入, 然后n1++
            if (nums1[n1] < nums2[n2]) {
                nums1[count] = nums1[n1];
                n1++;
            } else {
                nums1[count] = nums2[n2];
                n2++;
            }

            count++;
        }

        //放完之后, 两个必定有一个还没有放完. 也可能都放完, 放完的结果就是n1或者n2会大于索引
        while (n1 <= n + m - 1) {
            nums1[count] = nums1[n1];
            count++;
            n1++;
        }

        while (n2 <= n - 1) {
            nums1[count] = nums2[n2];
            count++;
            n2++;
        }

    }

    //辅助方法, array中所有元素向后移动n个位置, 移动之后, 数组实际开始的索引就是n
    private void move(int[] array, int m, int n) {
        for (int index = m - 1; index >= 0; index--) {
            array[index + n] = array[index];
        }
    }

    public static void main(String[] args) {
        int[] array1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] array2 = new int[]{2, 5, 8};

        Solution solution = new Solution();

        solution.merge(array1, 3, array2, 3);

        System.out.println(Arrays.toString(array1));

    }


}