package datastructure.chapter08;

import java.util.Arrays;

public class BubbleSort {

    //冒泡排序, 本质就是从数组的头部开始, 不断看谁大, 就交换位置, 最后交换到最后的一定是最大的,然后对剩下的数组再重复这个过程
    //所以排序可以分为两个部分
    //一个辅助功能方法, 用于将指定的开始索引和结束索引中的最大值不断交换到最后.
    //然后有一个排序方法, 就是通过传递参数.
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
     * 迭代方式实现冒泡排序的方法. 改进的冒泡排序, 每次追踪一下上次交换的发生的位置, 只会扫描到那个索引即可. 如果一次扫描中没有发生任何交换, 则说明数组有序, 结束排序
     *
     * @param array      要排序的数组
     * @param startIndex 要排序的部分开始索引
     * @param endIndex   要排序的部分的结束索引
     * @param reverse    是否降序
     * @param <T>        泛型参数, 实现Comparable接口
     */
    private static <T extends Comparable<? super T>> void sort(T[] array, int startIndex, int endIndex, boolean reverse) {

        checkArguments(array, startIndex, endIndex);
        if (startIndex != endIndex) {
            int lastSwapIndex = endIndex;

            //从start开始, 到endIndex之前一个元素即可, 因为是向后比较, 无需遍历到最后一个元素
            //但是如果上一次交换发生在某个位置, 下一次只需要扫描到那个位置的
            for (int i = startIndex; i < endIndex; i++) {
                //对于其中的每一个元素, 从其当前位置到最后的位置-1的位置, 不断与后边一个元素比较, 如果大于, 就交换位置
                //这里需要控制j循环的终点, 但是必须用一个中间变量承载, 不能即时变动
                int lastTimeIndex = lastSwapIndex;
                //每次都设置一个布尔, 假设本次没有发生任何交换
                boolean isSwaped = false;


                for (int j = startIndex; j < lastTimeIndex; j++) {
                    if (!reverse) {
                        if (array[j].compareTo(array[j + 1]) > 0) {
                            T temp = array[j + 1];
                            array[j + 1] = array[j];
                            array[j] = temp;
                            //发生了交换, 将lastSwapIndex设置为此时的j即可.
                            lastSwapIndex = j;
                            isSwaped = true;
                        }
                    } else {
                        if (array[j].compareTo(array[j + 1]) < 0) {
                            T temp = array[j + 1];
                            array[j + 1] = array[j];
                            array[j] = temp;
                            lastSwapIndex = j;
                            isSwaped = true;
                        }
                    }
                }

                //如果这一次没有发生任何交换, 则说明已经排序完毕
                if (!isSwaped) {
                    return;
                }


                System.out.println("遍历完第" + i + "个元素之后的lastSwapIndex=" + lastSwapIndex);
                System.out.println(Arrays.toString(array));

            }
        }

    }

    //递归思路是. 如果递归方法可以完成升序, 只需要将一个数组的最大值放到最后, 然后对前边的数组进行排序, 整个数组就有序了

    /**
     * 递归版本. 思路是, 将整个数组的最大值交换到最后, 然后对n-1数组排好序, 整个数组就有序了. 停机条件是数组长度为1就不用排了.
     *
     * @param array      要排序的数组
     * @param startIndex 要排序的部分开始索引
     * @param endIndex   要排序的部分的结束索引
     * @param reverse    是否降序
     * @param <T>        泛型参数, 实现Comparable接口
     */
    public static <T extends Comparable<? super T>> void recursionSort(T[] array, int startIndex, int endIndex, boolean reverse) {
        checkArguments(array, startIndex, endIndex);
        //如果不相等, 交换最大值到最后, 然后对前边n-1个排序
        if (startIndex != endIndex) {

            //将startIndex-endIndex之间的最大值冒泡到最后
            for (int j = startIndex; j < endIndex; j++) {
                if (!reverse) {
                    if (array[j].compareTo(array[j + 1]) > 0) {
                        T temp = array[j + 1];
                        array[j + 1] = array[j];
                        array[j] = temp;
                    }
                } else {
                    if (array[j].compareTo(array[j + 1]) < 0) {
                        T temp = array[j + 1];
                        array[j + 1] = array[j];
                        array[j] = temp;
                    }
                }
            }
            //对前边n-1个数组排序
            recursionSort(array, startIndex, endIndex - 1, reverse);
        }
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
