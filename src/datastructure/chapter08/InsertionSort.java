package datastructure.chapter08;

import java.util.Arrays;

public class InsertionSort {

    public static <T extends Comparable<? super T>> void insertionSort(T[] array, int n) {
        //遍历数组的元素

        if (n > array.length || n < 0) {

            throw new RuntimeException("n大于数组的个数或者n小于等于0.");
        }

        for (int i = 0; i < n; i++) {

            //取到当前元素
            T target = array[i];

            //反向遍历到数组的头部, 由于无需和自己比较, 所以j开始的索引是i-1
            for (int j = i - 1; j >= 0; j--) {
                //比较j+1与j索引位置的元素, 如果当前元素小于j索引位置的元素, 则应该将j位置的元素往后移动一个索引, 然后把这个元素插进去
                //等于元素就一点一点的交换到正确的位置
                if (array[j + 1].compareTo(array[j]) < 0) {
                    array[j + 1] = array[j];
                    array[j] = target;
                }
                //如果大于, 就什么也不做
            }
            System.out.println("第" + (i + 1) + "个元素排完之后的数组是: " + Arrays.toString(array));

        }
    }

    public static <T extends Comparable<? super T>> void insertionSort(T[] array) {
        insertionSort(array, array.length);
    }

    public static <T extends Comparable<? super T>> void insertLastToRightPlace(T[] array, int n) {
        //停机条件是n=1, 什么也不用干

        if (array.length == 1) {
            return;
        }


        //方法的核心功能就是将最后一个索引插入到合适的位置
        if (n > 1) {
            T lastElement = array[n - 1];
            //依然是不断交换, 注意索引, 这里只需要比较到数组的最后一个元素就行了
            for (int j = n - 1; j > 0; j--) {
                if (array[j - 1].compareTo(array[j]) > 0) {
                    array[j] = array[j - 1];
                    array[j - 1] = lastElement;
                }
            }
            //然后继续对剩下的部分进行操作
            insertLastToRightPlace(array, n - 1);
        }
    }

    public static <T extends Comparable<? super T>> void insertionRecursionSort(T[] array) {
        insertLastToRightPlace(array, array.length);
    }
}
