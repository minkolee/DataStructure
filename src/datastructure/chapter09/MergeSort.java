package datastructure.chapter09;

public class MergeSort {

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


    @SuppressWarnings("unchecked")
    private static <T extends Comparable<? super T>> T[] getTempArray(int length) {
        return (T[]) new Comparable<?>[length];
    }

    private static <T extends Comparable<? super T>> void merge(T[] array, int array1StartIndex, int array1EndIndex, int array2StartIndex, int array2EndIndex, T[] tempArray, boolean reverse) {


        //从前边的分析中可以知道, 数组的开始索引一定是array1StartIndex, 结束索引是array2EndIndex, 两个数组的起始索引分别是对应的start和end, 只要用几个循环就可以归并

        //临时数组获取临时数组的起始索引
        int tempArrayRangeStart = array1StartIndex;

        int startIndex = array1StartIndex;
        //先遍历数组1的元素, 只要小于, 根据reverse判断是否升序

        //只要两个数组有任意一个被遍历完
        while (array1StartIndex <= array1EndIndex && array2StartIndex <= array2EndIndex) {
            //对第一个数组进行遍历
            //升序排列, 谁小先把谁放进temp中
            if (!reverse) {
                //假如第一个数组的第一个元素小于第二个数组的第一个元素
                if (array[array1StartIndex].compareTo(array[array2StartIndex]) < 0) {
                    //将其放入临时数组中
                    tempArray[startIndex] = array[array1StartIndex];
                    //自增临时数组的索引和第一个数组的当前索引
                    startIndex++;
                    array1StartIndex++;
                } else {
                    //把数组二的第一个元素放入临时数组
                    tempArray[startIndex] = array[array2StartIndex];
                    startIndex++;
                    array2StartIndex++;
                }
            } else {
                //假如第一个数组的第一个元素大于第二个数组的第一个元素
                if (array[array1StartIndex].compareTo(array[array2StartIndex]) > 0) {
                    //将其放入临时数组中
                    tempArray[startIndex] = array[array1StartIndex];
                    //自增临时数组的索引和第一个数组的当前索引
                    startIndex++;
                    array1StartIndex++;
                } else {
                    //把数组二的第一个元素放入临时数组
                    tempArray[startIndex] = array[array2StartIndex];
                    startIndex++;
                    array2StartIndex++;
                }
            }
        }

        //执行到这里, 一定有一个数组已经被遍历完, 检查那个没有被遍历完的数组. 此时startIndex指向临时数组中的空位

        //如果数组1没有被遍历完, 将剩余部分复制到临时数组中
        if (array1StartIndex <= array1EndIndex) {
            for (int i = array1StartIndex; i <= array1EndIndex; i++) {
                tempArray[startIndex] = array[i];
                startIndex++;
            }
        }

        //如果数组2没有被遍历完, 将剩余部分复制到临时数组中
        if (array2StartIndex <= array2EndIndex) {
            for (int i = array2StartIndex; i <= array2EndIndex; i++) {
                tempArray[startIndex] = array[i];
                startIndex++;
            }
        }

        //最后将临时数组的部分复制到原始数组中, 数组末尾一定是array2EndIndex
        for (int j = tempArrayRangeStart; j <= array2EndIndex; j++) {
            array[j] = tempArray[j];
        }

    }

    private static <T extends Comparable<? super T>> void sort(T[] array, int startIndex, int endIndex, T[] tempArray, boolean reverse) {

        checkArguments(array, startIndex, endIndex);

        //停机条件是数组只有一项, 直接停止

        //两个索引不相等, 就归并
        if (startIndex != endIndex) {
            sort(array, startIndex, (startIndex + endIndex) / 2, reverse);
            sort(array, (startIndex + endIndex) / 2 + 1, endIndex, reverse);
            merge(array, startIndex, (startIndex + endIndex) / 2, (startIndex + endIndex) / 2 + 1, endIndex, tempArray, reverse);

        }

    }


    private static <T extends Comparable<? super T>> void sort(T[] array, int startIndex, int endIndex, boolean reverse) {
        //先创建一个临时的数组
        T[] tempArray = getTempArray(array.length);

        sort(array, startIndex, endIndex, tempArray, reverse);
    }


    /**
     * 检测参数合理性的方法
     *
     * @param array      要排序的数组
     * @param startIndex 要排序部分的开始索引
     * @param endIndex   要排序部分的结束索引
     * @param <T>        泛型参数, 实现Comparable接口
     */
    private static <T> void checkArguments(T[] array, int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex < 0 || startIndex > array.length - 1 || endIndex > array.length - 1 || startIndex > endIndex) {
            throw new IllegalArgumentException("索引超出范围. startIndex=" + startIndex + " endIndex=" + endIndex);
        }
    }
}
