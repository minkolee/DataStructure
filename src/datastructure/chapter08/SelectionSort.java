package datastructure.chapter08;

public class SelectionSort {

    public static <T extends Comparable<? super T>> void sort(T[] array, int n) {
        if (n > array.length) {
            throw new RuntimeException("数组的项比参数n要少!");
        }
        if (!(n == 0 || n == 1)) {
            //对数组中每一个元素, 先找到这个元素到结尾的最小值, 然后和自己替换
            for (int i = 0; i < n; i++) {
                int minindex = findMin(array, i, n - 1);

                //交换两个元素的位置
                T temp = array[i];
                array[i] = array[minindex];
                array[minindex] = temp;
            }
        }
    }

    public static <T extends Comparable<? super T>> void sort(T[] array) {
        sort(array, array.length);
    }

    private static <T extends Comparable<? super T>> int findMin(T[] array, int startIndex, int lastIndex) {
        int min = startIndex;

        for (int i = startIndex; i <= lastIndex; i++) {
            if (array[min].compareTo(array[i]) > 0) {
                min = i;
            }
        }
        return min;
    }

    public static <T extends Comparable<? super T>> void resursionSort(T[] array) {
        recursionFindMinAndSwap(array, 0);
    }
        //
    private static <T extends Comparable<? super T>> void recursionFindMinAndSwap(T[] array, int index) {
        //停机条件是当前索引等于数组的最大索引
        if (index != array.length - 1) {
            //找到当前索引到数组末尾的最小元素对应的索引
            int minIndex = findMin(array, index, array.length - 1);

            //交换第一个元素和最小元素的值
            T temp = array[minIndex];
            array[minIndex] = array[index];
            array[index] = temp;
            //然后继续对剩下的数组进行操作
            recursionFindMinAndSwap(array, index + 1);
        }

    }


}
