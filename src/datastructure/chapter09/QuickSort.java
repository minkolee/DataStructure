package datastructure.chapter09;

import datastructure.chapter08.InsertionSort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

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
     * 排序的私有核心方法, 由划分方法完成核心工作
     *
     * @param array      要排序的数组
     * @param startIndex 要排序的部分的起始索引
     * @param endIndex   要排序的部分的结束索引
     * @param reverse    是否升序, false为升序, true为降序
     * @param <T>        泛型参数,必须实现Comparable接口
     */
    private static <T extends Comparable<? super T>> void sort(T[] array, int startIndex, int endIndex, boolean reverse) {
        if (endIndex - startIndex + 1 < 10) {
            System.out.println("进行插入排序了");
            if (!reverse) {
                InsertionSort.sortBetweenIndex(array, startIndex, endIndex);
            } else {
                InsertionSort.sortBetweenIndexDesc(array, startIndex, endIndex);
            }

        } else {
            int pivotIndex = partition(array, startIndex, endIndex, reverse);
            sort(array, startIndex, pivotIndex - 1, reverse);
            sort(array, pivotIndex + 1, endIndex, reverse);
        }
    }

    /**
     * 用于辅助快速排序的最基本的划分方法
     *
     * @param array      要划分的数组
     * @param startIndex 数组的起始索引
     * @param endIndex   数组的结束索引
     * @param reverse    是降序还是升序
     * @param <T>        泛型参数, 实现Comparable接口
     * @return 返回枢轴对应的索引
     */
    private static <T extends Comparable<? super T>> int partition(T[] array, int startIndex, int endIndex, boolean reverse) {

        //目前是最基本的划分方法, 强行规定中枢是 (startIndex+endIndex)/2

        //既然这样, 对于长度是1和2的数组, 可以直接返回结果

        //长度是1的数组就是枢轴, 直接返回对应索引
        if (startIndex == endIndex) {
            return startIndex;
            //长度是2的数组, 根据reverse判断大小, 然后直接交换位置, 枢轴直接返回startIndex即可, 因为相邻的(startIndex+endIndex)/2 = startIndex
        } else if (endIndex - startIndex == 1) {
            //升序的情况下, 比较第一个数是不是比第二个大 ,如果大就交换位置, 否则不交换
            if (!reverse) {
                if (array[startIndex].compareTo(array[endIndex]) > 0) {
                    swap(array, startIndex, endIndex);
                }
                //降序情况下, 比较第一个数是不是比第二个小, 如果小就交换位置
            } else {
                if (array[startIndex].compareTo(array[endIndex]) < 0) {
                    swap(array, startIndex, endIndex);
                }
            }

            return (startIndex + endIndex) / 2;
            //长度为3及以上的数组
        } else {
            //先强行选择枢轴索引和对应的元素
            int pivot = (startIndex + endIndex) / 2;

            //这里新增确定三元枢轴排序的方法
            sortFirstMiddleLast(array, startIndex, pivot, endIndex, reverse);

            System.out.println("三元枢轴排过序之后是 startIndex=" + startIndex + " " + array[startIndex] + " pivot=" + pivot + " " + array[pivot] + " endIndex=" + endIndex + " " + array[endIndex]);

            T pivotElement = array[pivot];

//            System.out.println("枢轴元素是: " + pivotElement+" 索引是: "+pivot);

            //将枢轴元素与最后一个元素交换位置
            swap(array, pivot, endIndex);

            //然后开始各从两端向另外一端扫描

            //获取原来的起始索引和右边倒数第二个索引, 下边判断控制条件会用到
            int fromRightIndex = endIndex - 1;

            int initialIndex = startIndex;

            //startIndex小于endIndex的情况
            while (startIndex < fromRightIndex) {

                if (!reverse) {
                    //从数组开头向数组末尾扫描, 扫描大于pivot的元素, 这个循环停下的位置是大于pivotElement的位置. 如果都没有, 最大也就是endIndex的地方
                    while (array[startIndex].compareTo(pivotElement) <= 0 && startIndex < endIndex) {
                        startIndex++;
                    }

                    //从数组的倒数第二个元素开始向数组头扫描, 扫描小于pivot的元素, 这个循环停下的位置是小于pivotElement的位置, 最小也就是原来startIndex的地方
                    while (array[fromRightIndex].compareTo(pivotElement) >= 0 && fromRightIndex > initialIndex) {
                        fromRightIndex--;
                    }


                } else {
                    while (array[startIndex].compareTo(pivotElement) >= 0 && startIndex < endIndex) {
                        startIndex++;
                    }

                    //从数组的倒数第二个元素开始向数组头扫描, 扫描小于pivot的元素, 这个循环停下的位置是小于pivotElement的位置, 最小也就是原来startIndex的地方
                    while (array[fromRightIndex].compareTo(pivotElement) <= 0 && fromRightIndex > initialIndex) {
                        fromRightIndex--;
                    }


                }

                //如果startIndex小于fromRightIndex, 说明需要交换, 否则无需交换
                if (startIndex < fromRightIndex) {
                    swap(array, startIndex, fromRightIndex);
                }
            }
//            System.out.println("-------------------");
//            System.out.println("startIndex="+startIndex);
//            System.out.println("fromRightIndex="+fromRightIndex);
//            System.out.println("-------------------");

            //循环执行完毕之后, 要么两个索引相等A=B, 要么A和B互换位置导致A>B, 此时A的位置, 必定是大于等于枢轴的位置, 因此交换A与末尾元素(就是枢轴)
            swap(array, startIndex, endIndex);
//            System.out.println("排完之后的枢轴位置是: " + startIndex);
            //返回此时的实际枢轴所在位置
            return startIndex;
        }


    }

    /**
     * 交换数组中两个指定索引的元素的位置的方法
     *
     * @param array       目的数组
     * @param firstIndex  第一个索引
     * @param secondIndex 第二个索引
     * @param <T>         泛型参数, 实现Comparable接口
     */
    private static <T extends Comparable<? super T>> void swap(T[] array, int firstIndex, int secondIndex) {
        T temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }


    private static <T extends Comparable<? super T>> void sortFirstMiddleLast(T[] array, int firstIndex, int middleIndex, int lastIndex, boolean reverse) {

        T a = array[firstIndex];
        T b = array[middleIndex];
        T c = array[lastIndex];

        //默认是升序的情况
        //a>=b的情况
        if (a.compareTo(b) >= 0) {
            //如果b>=c, 按照 c b a 排列
            if (b.compareTo(c) >= 0) {
                array[firstIndex] = c;
                array[middleIndex] = b;
                array[lastIndex] = a;
                //如果a>=c>b, 按照a c b排列
            } else if (a.compareTo(c) >= 0) {
                array[firstIndex] = b;
                array[middleIndex] = c;
                array[lastIndex] = a;
                //如果c>a>=b, 按照b a c 排列
            } else {
                array[firstIndex] = b;
                array[middleIndex] = a;
                array[lastIndex] = c;
            }
            //a<b的情况
        } else {
            //b>a>c
            if (a.compareTo(c) >= 0) {
                array[firstIndex] = c;
                array[middleIndex] = a;
                array[lastIndex] = b;
                //b>c>a
            } else if (b.compareTo(c) >= 0) {
                array[firstIndex] = a;
                array[middleIndex] = c;
                array[middleIndex] = b;
                //c>b>a
            } else {
                array[firstIndex] = a;
                array[middleIndex] = b;
                array[lastIndex] = c;
            }
        }

        //如果降序, 交换一下最大和最小值即可.
        if (reverse) {
            swap(array, firstIndex, lastIndex);
        }

    }


}
