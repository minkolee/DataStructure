package datastructure.chapter26;

import java.util.PriorityQueue;

public class MaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T> {

    //实际实现堆的数组
    private T[] heap;

    //指向最后一个元素的索引
    private int lastIndex;

    //老套路了, 安全初始化标志
    boolean initialized = false;

    private static final int DEFAULT_CAPACITY = 25;

    private static final int MAX_CAPACITY = 10000;

    MaxHeap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    MaxHeap(int size) {
        if (size > MAX_CAPACITY) {
            size = MAX_CAPACITY;
        }

        if (size < DEFAULT_CAPACITY) {
            size = DEFAULT_CAPACITY;
        }

        heap = (T[]) new Comparable[size + 1];
        lastIndex = 0;
        initialized = true;
    }

    public T[] toArray() {
        return heap;
    }


    @Override
    public void add(T newEntry) {

        checkCapacity();


        //先把元素放入最后一格
        lastIndex = lastIndex + 1;
        heap[lastIndex] = newEntry;

        int index = lastIndex;

        while (index != 1) {
            if (heap[index].compareTo(heap[index / 2]) > 0) {
                //交换
                T temp = heap[index];
                heap[index] = heap[index / 2];
                heap[index / 2] = temp;
            }
            index = index / 2;
        }

    }

    @Override
    public T removeMax() {
        T result = null;

        if (!isEmpty()) {
            int index = 1;

            //交换根元素和最后一个元素, 顺便就给result赋值, 然后将最后一个位置置为null, 之后减少lastIndex
            result = heap[1];
            heap[1] = heap[lastIndex];
            heap[lastIndex] = null;
            //然后减少lastIndex, lastIndex用于后边下沉判断

            lastIndex = lastIndex - 1;

            reheap(index);

        }

        return result;
    }

    @Override
    public T getMax() {
        if (isEmpty()) {
            return null;
        } else {
            return heap[1];
        }
    }

    @Override
    public boolean isEmpty() {
        return lastIndex == 0;
    }

    @Override
    public int getSize() {
        return lastIndex;
    }

    public int getRealSize() {
        return heap.length;
    }

    @Override
    public void clear() {
        for (int i = 1; i <= lastIndex; i++) {
            heap[i] = null;
        }
        lastIndex = 0;
    }

    private void reheap(int index) {

        boolean done = false;
        //还存在左节点的情况下, 找到两个子节点的较大项(也许没有右节点), 然后进行比较, 只要小于, 就交换
        while (!done && 2 * index <= lastIndex) {

            //如果存在右节点
            if (2 * index + 1 <= lastIndex) {
                //如果左节点大于右节点
                if (heap[index * 2].compareTo(heap[index * 2 + 1]) > 0) {

                    //要下沉节点小于左节点, 进行交换
                    if (heap[index].compareTo(heap[index * 2]) < 0) {
                        T temp = heap[index];
                        heap[index] = heap[index * 2];
                        heap[index * 2] = temp;
                        //更新当前节点的index到左节点的位置
                        index = index * 2;

                        //下沉节点大于两个较大的, 则已经到达正确位置
                    } else {
                        done = true;
                    }
                    //如果左节点小于等于右节点, 即右节点较大
                } else {
                    //要下沉节点小于右节点, 进行交换
                    if (heap[index].compareTo(heap[index * 2 + 1]) < 0) {

                        T temp = heap[index];
                        heap[index] = heap[index * 2 + 1];
                        heap[index * 2 + 1] = temp;

                        //更新当前节点的index到右节点的位置
                        index = index * 2 + 1;
                        //下沉节点大于两个较大的, 则已经到达正确位置
                    } else {
                        done = true;
                    }
                }
                //如果不存在右节点, 直接比较左节点
            } else {
                //要下沉节点小于左节点, 进行交换
                if (heap[index].compareTo(heap[index * 2]) < 0) {
                    T temp = heap[index];
                    heap[index] = heap[index * 2];
                    heap[index * 2] = temp;
                    //下沉节点大于两个较大的, 则已经到达正确位置
                    index = index * 2;
                } else {
                    done = true;
                }
            }

        }
    }


    public MaxHeap(T[] entryArray) {
        this(entryArray.length);

        //将数组内容复制到内部数组的从1开始的位置
        System.arraycopy(entryArray, 0, heap, 1, entryArray.length);

        lastIndex = entryArray.length;

        //反向重新整理堆
        for (int index = lastIndex / 2; index >= 1; index--) {
            reheap(index);
        }

    }


    private static <T extends Comparable<? super T>> void reheap(T[] array, int startIndex, int endIndex) {
        boolean done = false;
        //由于这里的索引正常化, 所以左节点索引会变化
        int leftChildIndex = 2 * startIndex + 1;

        while (!done && leftChildIndex <= endIndex) {

            //确定那一边比较大, 获取较大的节点对应的数组索引
            int largerChildIndex = leftChildIndex;
            //如果有右节点
            if (leftChildIndex + 1 <= endIndex) {
                //如果右节点是比较大的点
                if (array[leftChildIndex + 1].compareTo(array[leftChildIndex]) > 0) {
                    largerChildIndex = leftChildIndex + 1;
                }
            }

            //比较首节点与较大的子节点, 如果小于就交换, 然后重新设置startIndex到交换后的节点, leftChildIndex到交换后的节点的左子节点
            if (array[startIndex].compareTo(array[largerChildIndex]) < 0) {

                T temp = array[startIndex];
                array[startIndex] = array[largerChildIndex];
                array[largerChildIndex] = temp;

                startIndex = largerChildIndex;
                leftChildIndex = largerChildIndex * 2 + 1;
            } else {
                done = true;
            }
        }
    }

    private static <T extends Comparable<? super T>> void arrangToHeap(T[] array) {
        for (int i = array.length / 2; i >= 0; i--) {
            reheap(array, i, array.length - 1);
        }
    }

    public static <T extends Comparable<? super T>> void sort(T[] array) {
        //将数组弄成堆
        arrangToHeap(array);

        //第一次交换首尾元素

        T temp = array[0];
        array[0] = array[array.length - 1];
        array[array.length - 1] = temp;

        int count = array.length;

        //然后开始不断交换首尾元素并缩短范围
        //由于下沉过程会自动结束, 因此无需判断实际操作的数组长度
        for (int lastIndex = count - 2; lastIndex >= 0; lastIndex--) {
            reheap(array, 0, lastIndex);
            T temp2 = array[0];
            array[0] = array[lastIndex];
            array[lastIndex] = temp2;
        }
    }


    @SuppressWarnings("unchecked")
    private void checkCapacity() {

//        System.out.println("检查容量");

        //如果已经满了, 即lastIndex = MAX_CAPACITY, 抛异常
        if (lastIndex + 1 > MAX_CAPACITY) {
            throw new RuntimeException("数组已经达到最大长度");
        }

        if (lastIndex + 1 == heap.length) {
//            System.out.println("需要扩大");
            //否则需要在扩大一倍和上限之间进行选择, 实际的数组长度应该是 lastIndex*2+1 和 MAX_CAPACITY+1 两种的较小值

            int newLength = Math.min((lastIndex * 2 + 1), MAX_CAPACITY + 1);

            T[] newArray = (T[]) new Comparable[newLength];

            if (lastIndex + 1 >= 0) System.arraycopy(heap, 0, newArray, 0, lastIndex + 1);

            heap = newArray;
        }
    }



}
