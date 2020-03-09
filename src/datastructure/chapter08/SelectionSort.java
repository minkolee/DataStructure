package datastructure.chapter08;

import java.util.Arrays;

/**
 * 选择排序类
 */
public class SelectionSort {

    /**
     * 给数组开头的count个元素进行升序排列
     *
     * @param array 数组
     * @param count 从开始要排序的元素的数量
     * @param <T>   泛型参数,必须实现Comparable接口
     */
    public static <T extends Comparable<? super T>> void sortFromStart(T[] array, int count) {

        //判断参数是否合理, 由于核心函数会判断参数是否有问题, 所以这里只需要判断count即可, 等于0或者1直接返回
        //如果count等于其他值, 会由核心函数进行判断
        if (count == 0 || count == 1) {
            return;
        }

        //调用核心函数, 开始索引为0, 结束索引为count-1即可
        sort(array, 0, count - 1, false);
    }

    /**
     * 给数组开头的count个元素进行降序排列
     *
     * @param array 数组
     * @param count 从开始要排序的元素的数量
     * @param <T>   泛型参数,必须实现Comparable接口
     */
    public static <T extends Comparable<? super T>> void sortFromStartDesc(T[] array, int count) {

        //判断参数是否合理, 由于核心函数会判断参数是否有问题, 所以这里只需要判断count即可, 等于0或者1直接返回
        //如果count等于其他值, 会由核心函数进行判断
        if (count == 0 || count == 1) {
            return;
        }

        //调用核心函数, 开始索引为0, 结束索引为count-1即可
        sort(array, 0, count - 1, true);
    }


    /**
     * 给从数组末尾向前数起的count个元素进行升序排序
     *
     * @param array 要排序的数组
     * @param count 末尾的count个元素
     * @param <T>   泛型参数,必须实现Comparable接口
     */
    public static <T extends Comparable<? super T>> void sortFromEnd(T[] array, int count) {

        //判断参数是否合理, 由于核心函数会判断参数是否有问题, 所以这里只需要判断count即可, 等于0或者1直接返回
        //如果count等于其他值, 会由核心函数进行判断
        if (count == 0 || count == 1) {
            return;
        }

        //调用核心函数, 开始索引为array.length-1-count, 结束索引为 array.length-1
        recursionSort(array, array.length - count, array.length - 1, false);
    }

    /**
     * 给从数组末尾向前数起的count个元素进行降序排序
     *
     * @param array 要排序的数组
     * @param count 末尾的count个元素
     * @param <T>   泛型参数,必须实现Comparable接口
     */
    public static <T extends Comparable<? super T>> void sortFromEndDesc(T[] array, int count) {

        //判断参数是否合理, 由于核心函数会判断参数是否有问题, 所以这里只需要判断count即可, 等于0或者1直接返回
        //如果count等于其他值, 会由核心函数进行判断
        if (count == 0 || count == 1) {
            return;
        }

        //调用核心函数, 开始索引为array.length-1-count, 结束索引为 array.length-1
        recursionSort(array, array.length - count, array.length - 1, true);
    }

    /**
     * 对数组全部元素进行升序排列, 内部直接调用核心私有方法或者另外一个排序前N个的都可以
     *
     * @param array 要排序的数组
     * @param <T>   泛型参数,必须实现Comparable接口
     */
    public static <T extends Comparable<? super T>> void sort(T[] array) {
        sort(array, 0, array.length - 1, false);
    }

    /**
     * 对数组全部元素进行降序排列, 内部直接调用核心私有方法或者另外一个排序前N个的都可以
     *
     * @param array 要排序的数组
     * @param <T>   泛型参数,必须实现Comparable接口
     */
    public static <T extends Comparable<? super T>> void sortDesc(T[] array) {
        sort(array, 0, array.length - 1, true);
    }


    /**
     * 对指定索引范围内的数组元素进行升序排序, 包含起始索引与结束索引
     *
     * @param array      要排序的数组
     * @param startIndex 要排序的部分的起始索引
     * @param endIndex   要排序的部分的结束索引
     * @param <T>        泛型参数,必须实现Comparable接口
     */
    public static <T extends Comparable<? super T>> void sortBetweenIndex(T[] array, int startIndex, int endIndex) {
        //内部直接调用私有核心方法即可
        sort(array, startIndex, endIndex, false);
    }

    /**
     * 对指定索引范围内的数组元素进行降序排序, 包含起始索引与结束索引
     *
     * @param array      要排序的数组
     * @param startIndex 要排序的部分的起始索引
     * @param endIndex   要排序的部分的结束索引
     * @param <T>        泛型参数,必须实现Comparable接口
     */
    public static <T extends Comparable<? super T>> void sortBetweenIndexDesc(T[] array, int startIndex, int endIndex) {
        //内部直接调用私有核心方法即可
        sort(array, startIndex, endIndex, true);
    }


    /**
     * 对起始索引索引和结束索引之间(包含起始索引和结束索引)的部分进行排序, 就地排序, 不返回新数组.
     * 本方法是数组排序的核心私有方法, 对外暴露的其他方法内部调用该方法.
     * 数组元素需要实现Comparable接口
     *
     * @param array      要排序的数组,
     * @param startIndex 要进行排序部分的起始索引
     * @param endIndex   要进行排序部分的结束索引
     * @param reverse    布尔类型, 是否降序排序, 为true表示降序排序, 为false表示升序排序
     * @param <T>        泛型参数,必须实现Comparable接口
     */
    private static <T extends Comparable<? super T>> void sort(T[] array, int startIndex, int endIndex, boolean reverse) {
        //判断数组长度, 如果为0或者1, 直接返回
        if (array.length == 0 || array.length == 1) {
            return;
        }

        //判断两个索引参数是否符合要求
        if (startIndex < 0 || startIndex > array.length - 1 || endIndex < 0 || endIndex > array.length - 1 || startIndex > endIndex) {
            throw new IllegalArgumentException("索引超出范围. startIndex=" + startIndex + " endIndex=" + endIndex);
        }

        //主排序算法, 对于数组中每一个元素, 获取其到endIndex之间的最小值, 然后交换二者的位置, 注意, 其实只需要遍历到倒数第二个元素即可, 因为不管交换不交换, 结果都正确.
        for (int i = startIndex; i < endIndex; i++) {
            //根据是否升序或者降序, 查找当前索引到结束索引之间的最小/大值对应的索引
            int swapIndex;
            if (reverse) {
                swapIndex = findMaxIndex(array, i, endIndex);
            } else {
                swapIndex = findMinIndex(array, i, endIndex);
            }

            //交换当前索引和最大/最小值索引的内容
            swap(array, i, swapIndex);

            //打印每次排序后的内容
            System.out.println(Arrays.toString(array));
        }
    }

    /**
     * 递归版本的核心排序私有方法, 方法原理是, 寻找当前数组的最小/大值, 与第一个进行交换. 然后对数组的第二个元素开始到结束的部分进行同样的空操作, 直到剩余部分长度为1结束.
     * 这个递归方法的名称应该叫交换第一个元素与最大/最小值更加合适
     *
     * @param array      要排序的数组
     * @param startIndex 开始的索引
     * @param endIndex   结束的索引
     * @param reverse    是否反向(降序)排列, true为降序, false为升序
     * @param <T>        泛型参数,必须实现Comparable接口
     */
    private static <T extends Comparable<? super T>> void recursionSort(T[] array, int startIndex, int endIndex, boolean reverse) {
        //基础的参数判断与非递归版本一致
        if (array.length == 0 || array.length == 1) {
            return;
        }

        //判断两个索引参数是否符合要求
        if (startIndex < 0 || startIndex > array.length - 1 || endIndex < 0 || endIndex > array.length - 1 || startIndex > endIndex) {
            throw new IllegalArgumentException("索引超出范围. startIndex=" + startIndex + " endIndex=" + endIndex);
        }

        //停机条件是startIndex=endIndex
        if (startIndex != endIndex) {
            //根据是否降序取最小/最大值对应的索引
            int swapIndex;
            if (reverse) {
                swapIndex = findMaxIndex(array, startIndex, endIndex);
            } else {
                swapIndex = findMinIndex(array, startIndex, endIndex);
            }
            //交换第一个索引与最大/最小值对应的索引的内容
            swap(array, startIndex, swapIndex);
            //对从start+1开始到endIndex的部分继续排序
            recursionSort(array, startIndex + 1, endIndex, reverse);
        }

    }


    /**
     * 寻找一个数组在起始索引和结束索引之间的最小值对应的索引, 数组元素需要实现Comparable接口. 私有方法, 为支持排序方法.
     *
     * @param array      数组对象
     * @param startIndex 起始索引
     * @param lastIndex  结束索引
     * @param <T>        泛型参数,必须实现Comparable接口
     * @return 在起始索引和结束索引之间的最小值对应的索引
     */
    private static <T extends Comparable<? super T>> int findMinIndex(T[] array, int startIndex, int lastIndex) {
        int min = startIndex;

        for (int i = startIndex; i <= lastIndex; i++) {
            if (array[min].compareTo(array[i]) > 0) {
                min = i;
            }
        }
        return min;
    }


    /**
     * 寻找一个数组在起始索引和结束索引之间的最大值对应的索引, 数组元素需要实现Comparable接口. 私有方法, 为支持排序方法.
     *
     * @param array      数组对象
     * @param startIndex 起始索引
     * @param lastIndex  结束索引
     * @param <T>        泛型参数,必须实现Comparable接口
     * @return 在起始索引和结束索引之间的最大值对应的索引
     */
    private static <T extends Comparable<? super T>> int findMaxIndex(T[] array, int startIndex, int lastIndex) {
        int max = startIndex;

        for (int i = startIndex; i <= lastIndex; i++) {
            if (array[max].compareTo(array[i]) < 0) {
                max = i;
            }
        }
        return max;
    }

    /**
     * 交换数组中两个元素的方法, 不返回任何值, 就地交换. 私有方法, 为支持排序方法
     *
     * @param array  数组
     * @param index1 要交换的两个元素的其中一个索引
     * @param index2 要交换的两个元素的另外一个索引
     * @param <T>    泛型参数,必须实现Comparable接口
     */
    private static <T> void swap(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
