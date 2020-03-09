package datastructure.chapter08;


import java.util.Arrays;

public class ShellSort {

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
        sort(array, array.length - count, array.length - 1, false);
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
        sort(array, array.length - count, array.length - 1, true);
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
     * 包装后的希尔排序核心排序方法, 是另外一个重载sort方法的包装
     *
     * @param array      要排序的数组
     * @param startIndex 要排序的范围的起始索引
     * @param endIndex   要排序的范围的结束索引
     * @param reverse    是否升序, false为升序, true为降序
     * @param <T>        泛型参数,必须实现Comparable接口
     */
    private static <T extends Comparable<? super T>> void sort(T[] array, int startIndex, int endIndex, boolean reverse) {
        int step = array.length / 2;

        while (step > 0) {

            if (step % 2 == 0) {
                //当step是偶数的时候, 将其加1传递给排序方法
                sort(array, startIndex, endIndex, step + 1, reverse);
            } else {
                //如果是奇数就不做变更
                sort(array, startIndex, endIndex, step, reverse);
            }

            step = step / 2;
        }
    }


    /**
     * 希尔排序的单次排以步长间隔的数组的方法, 迭代版本
     *
     * @param array      要排序的数组
     * @param startIndex 要排序部分开始的索引
     * @param endIndex   要排序部分结束的索引
     * @param step       希尔排序所需的步长, 初始是数组长度/2
     * @param reverse    是否降序排列, true为降序, false为升序
     * @param <T>        泛型参数,必须实现Comparable接口
     */
    private static <T extends Comparable<? super T>> void sort(T[] array, int startIndex, int endIndex, int step, boolean reverse) {
        if (array.length == 0 || array.length == 1) {
            return;
        }

        checkArguments(array, startIndex, endIndex);

        //希尔排序要遍历的元素, 实际上是从startIndex开始, 每次加上步长的元素, 所以i每次应该增加step长度. 这里就还是从头遍历, 方便控制索引
        //
        for (int i = startIndex; i <= endIndex; i += step) {

            //核心方法也需要修改, 并不是插入到startIndex 到 i 之间的所有元素, 而是startIndex 到 i 之间以 step为间隔的元素.
            insertIntoArray(array, startIndex, i, step, reverse);

        }
    }

    /**
     * 将currentIndex位置上的元素, 合理插入到 按照步长分割的其左侧数组中的方法
     * 本方法辅助希尔排序方法完成工作
     *
     * @param array        要排序的数组
     * @param startIndex   要排序的部分的开始索引位置
     * @param currentIndex 当前索引位置
     * @param step         希尔排序所需的步长
     * @param reverse      是否降序, true为降序, false为升序
     * @param <T>          泛型参数,必须实现Comparable接口
     */
    private static <T extends Comparable<? super T>> void insertIntoArray(T[] array, int startIndex, int currentIndex, int step, boolean reverse) {

        T element = array[currentIndex];
        int index = currentIndex;

        //这里需要比较 当前元素, 也就是currentIndex, 与其 减去步长之后的元素, 不过不能超过数组左边界, 所以加上一个判断条件
        while (index > startIndex && index - step >= 0) {

            //当前元素与减去步长之后的元素比较
            if (!reverse) {
                if (element.compareTo(array[index - step]) < 0) {
                    array[index] = array[index - step];
                    index -= step;
                } else {
                    break;
                }
            } else {
                if (element.compareTo(array[index - step]) > 0) {
                    array[index] = array[index - step];
                    index -= step;
                } else {
                    break;
                }
            }

        }

        //循环结束的时候, index就指向应该插入的位置, 如果element是最小, 此时的索引就是0, 否则就是某个应该插入的位置. 其之前的位置都已经被右移了一格
        //将element插入到当前位置
        array[index] = element;
    }

    /**
     * 检查参数是否出现问题的函数, 如果参数有问题, 抛出运行时错误
     *
     * @param array      要排序的数组
     * @param startIndex 要排序部分的起始索引
     * @param endIndex   要排序部分的结束索引
     * @param <T>        泛型参数,必须实现Comparable接口
     */
    private static <T> void checkArguments(T[] array, int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex < 0 || startIndex > array.length - 1 || endIndex > array.length - 1 || startIndex > endIndex) {
            throw new IllegalArgumentException("索引超出范围. startIndex=" + startIndex + " endIndex=" + endIndex);
        }
    }
}
