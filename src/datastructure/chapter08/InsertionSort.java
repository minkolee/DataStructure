package datastructure.chapter08;


import java.util.Arrays;

public class InsertionSort {

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
     * 插入排序的核心方法, 迭代版本
     *
     * @param array      要排序的数组
     * @param startIndex 要排序部分开始的索引
     * @param endIndex   要排序部分结束的索引
     * @param reverse    是否降序排列, true为降序, false为升序
     * @param <T>        泛型参数,必须实现Comparable接口
     */
    private static <T extends Comparable<? super T>> void sort(T[] array, int startIndex, int endIndex, boolean reverse) {

        if (array.length == 0 || array.length == 1) {
            return;
        }

        checkArguments(array, startIndex, endIndex);
        //由于插入排序的特点, 只需要从数组的第二个元素开始遍历, 一直到endIndex, 即范围是 startIndex+1 to endIndex
        //从要排序部分的第二个元素一直到要排序部分的最后一个元素遍历
        for (int i = startIndex + 1; i <= endIndex; i++) {

            //将当前元素插入到startIndex 到 i 之间的正确位置
            insertIntoArray(array, startIndex, i, reverse);
        }
    }

    /**
     * 将currentIndex位置上的元素, 合理插入到 startIndex - (currentIndex-1) 范围内的数组中的方法
     * 本方法是插入算法中将某一个位置的元素插入到其正确位置的核心方法, 辅助核心排序方法完成工作
     * 注意虽然是看上去好像插入范围是 startIndex - currentIndex, 但其实如果在第一次比较的时候就跳出循环, 等于原地更新自己, 没有任何变化, 只要有插入动作, 就是插入到 startIndex - (currentIndex-1) 的范围中
     *
     * @param array        要排序的数组
     * @param startIndex   要排序的部分的开始索引位置
     * @param currentIndex 当前索引位置
     * @param reverse      是否降序, true为降序, false为升序
     * @param <T>          泛型参数,必须实现Comparable接口
     */
    private static <T extends Comparable<? super T>> void insertIntoArray(T[] array, int startIndex, int currentIndex, boolean reverse) {

        T element = array[currentIndex];
        int index = currentIndex;

        //这里注意, 当前元素不需要一直移动到startIndex, 只需要移动到startIndex+1的位置即可, 在这个位置上比较左边一个元素的位置即可. 如果移动到数组尽头还去比较左侧元素, 会导致越界.
        while (index > startIndex) {
            //如果当前元素小于其左边元素, 说明其至少应该在左边元素的左边, 所以将左边元素右移一格, 然后继续比较当前元素与再左边元素
            if (!reverse) {
                if (element.compareTo(array[index - 1]) < 0) {
                    array[index] = array[index - 1];
                    index--;
                } else {
                    break;
                }
            } else {
                if (element.compareTo(array[index - 1]) > 0) {
                    array[index] = array[index - 1];
                    index--;
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
     * 递归插入排序的方法, 是核心私有方法
     * 递归思想是从迭代观察发现, 每一个元素的左边已经是被排序好的, 排序的方法就是将那个元素插入左边已经排序好的序列.
     * 本方法既然可以进行排序, 只要数组长度不为1, 就先将数组最后一个元素的左边部分全部排序好, 再将最后一个元素插入到已经排序好的部分.
     *
     * @param array      要排序的数组
     * @param startIndex 要排序部分的开始索引
     * @param endIndex   要排序部分结束的索引
     * @param reverse    是否降序排列, true为降序, false为升序
     * @param <T>        泛型参数,必须实现Comparable接口
     */
    public static <T extends Comparable<? super T>> void recursionSort(T[] array, int startIndex, int endIndex, boolean reverse) {
        //判断过程和原来一样, 其实也可以抽取出来
        if (array.length == 0 || array.length == 1) {
            return;
        }

        checkArguments(array, startIndex, endIndex);

        //只要数组长度不为1
        if (startIndex != endIndex) {
            //对数组n-1的部分进行排序
            recursionSort(array, startIndex, endIndex - 1, reverse);
            //将当前最后一个索引 endIndex 对应的元素插入到 start - endIndex 之间
            recursionSwap(array, startIndex, endIndex, reverse);
        }

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

    /**
     * 将insertIntoArray方法改写而成的递归方法
     * 递归方法的功能是交换一个数组的最右侧两个元素. 停机条件是根据reverse的比较大小.
     * 例如,如果reverse是 false 表示 升序排列, 则停机条件首先是两个索引相等, 无需插入, 之后停机条件是当前元素大于等于其左侧元素
     * 如果当前元素小于其左侧元素, 交换两个元素的位置, 当前元素的索引就减少了1, 于是继续对当前元素为末尾的数组调用递归方法, 这样递归方法就始终跟踪一个元素.
     *
     * @param array        要插入的数组
     * @param startIndex   插入部分的起始索引
     * @param currentIndex 要将哪个索引的元素插入到起始到这个索引的部分, 参数就是那个索引
     * @param <T>          泛型参数,必须实现Comparable接口
     */
    public static <T extends Comparable<? super T>> void recursionSwap(T[] array, int startIndex, int currentIndex, boolean reverse) {

        if (currentIndex != startIndex) {

            if (!reverse) {
                if (array[currentIndex].compareTo(array[currentIndex - 1]) < 0) {
                    T temp = array[currentIndex];
                    array[currentIndex] = array[currentIndex - 1];
                    array[currentIndex - 1] = temp;
                    recursionSwap(array, startIndex, currentIndex - 1, false);
                }
            } else {
                if (array[currentIndex].compareTo(array[currentIndex - 1]) > 0) {
                    T temp = array[currentIndex];
                    array[currentIndex] = array[currentIndex - 1];
                    array[currentIndex - 1] = temp;
                    recursionSwap(array, startIndex, currentIndex - 1, true);
                }
            }

        }
    }


}