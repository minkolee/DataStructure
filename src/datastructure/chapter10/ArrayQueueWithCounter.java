package datastructure.chapter10;

import java.util.Arrays;

/**
 * 带有计数器的队列类
 */
public class ArrayQueueWithCounter<T> implements QueueInterface<T> {

    /**
     * 数组初始长度, 一开始队列的容量是16, 上限是10000
     */
    private int length = 8;
    private static int MAX_SIZE = 17;

    //数组中已经放入的元素个数
    private int count;

    /**
     * 用于表示队列空的特殊情况, 即指向队列头和尾的索引都是-1
     */
    private int firstIndex;
    private int lastIndex;

    private T[] array;

    @SuppressWarnings("unchecked")
    public ArrayQueueWithCounter() {
        count = 0;
        firstIndex = -1;
        lastIndex = -1;
        array = (T[]) new Object[length];
    }

    /**
     * 入队的方法.当队列为空的时候, 固定将项目添加在第一项, 然后设置firstIndex和lastIndex都为0
     * //不为空的情况下, 检测长度有没有已经满了. 已经满了, 扩大数组长度, 不满则无需扩大.
     * @param entry 要进入队列的项
     */
    @Override
    public void enqueue(T entry) {

        if (isEmpty()) {
            array[0] = entry;
            firstIndex = 0;
            lastIndex = 0;
        } else {
            //满了就需要扩容
            if (count == length) {
                //无法扩容就抛异常
                increaseCapacity();
            }

            //添加一项并将lastIndex指向那一项, 要对lastIndex取模
            lastIndex = (lastIndex + 1) % length;
            array[lastIndex] = entry;
        }
        count++;
    }


    /**
     * 出队方法. 如果队列空就返回null.
     * 如果不空,先要获取队列头的元素, 并数组对应位置清空, 之后根据队列长度判断是否要恢复到原始状态
     * @return 返回出队的项
     */
    @Override
    public T dequeue() {

        if (isEmpty()) {
            return null;
        } else {
            T result = (T) array[firstIndex];
            array[firstIndex] = null;
            //只有一项, 获取之后, 将全部参数重新恢复到初始.
            if (count == 1) {
                clear();
            } else {
                //超过一项, 弹完之后, 应该将firstIndex向后移动一个位置
                firstIndex = (firstIndex + 1) % length;
                count--;
            }
            return result;
        }
    }

    /**
     * 查看队列头部的方法. 很简单.
     * @return 返回队列头部的项
     */
    @Override
    public T getFront() {
        if (isEmpty()) {
            return null;
        } else {
            return (T)array[firstIndex];
        }
    }

    /**
     * 其实只需要判断这三者的任何一个即可.
     * @return 如果空返回True
     */
    @Override
    public boolean isEmpty() {
        return firstIndex == -1 && lastIndex == -1 && count == 0;
    }

    /**
     * 清除队列的方法, 重置所有内容到初始化状态
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        firstIndex = -1;
        lastIndex = -1;
        count = 0;
        length = 16;
        array = (T[]) new Object[length];
    }

    /**
     * 扩容的方法, 先修改length变量, 然后创建新数组, 将原来数组复制到新数组中, 用新数组取代原来的数组.
     * 之后重新设置firstIndex和lastIndex
     */
    @SuppressWarnings("unchecked")
    private void increaseCapacity() {

        //操作length变量, 即当前内部数组的总长度
        if (length == MAX_SIZE) {
            throw new RuntimeException("队列已经达到最大长度, 无法再入队.");
        } else {
            length = Math.min(length * 2, MAX_SIZE);
        }

        T[] newArray = (T[]) new Object[length];

        //将旧数组中的内容复制到新数组, 如何复制呢, 只需要从firstIndex开始, 不断复制count个数据就可以了.

        int startIndex = 0;
        int oldLength = array.length;
        while (startIndex != count) {
            newArray[startIndex] = array[firstIndex];
            firstIndex = (firstIndex + 1) % oldLength;
            startIndex++;
        }

        //循环结束之后所有的原来数组的东西都复制到新数组从0开始的位置, 用新数组取代原来的数组, 之后重新设置 firstIndex 和 lastIndex
        array = newArray;
        firstIndex = 0;
        lastIndex = count - 1;
    }

    public void show() {
        System.out.println(Arrays.toString(array));
        System.out.print("当前firstIndex = " + firstIndex);
        System.out.print("\t当前lastIndex = " + lastIndex);
        System.out.print("\t当前count = " + count);
        System.out.println("\t当前length = " + length);
        System.out.println("----------------------------------------------------------------------------------------");

    }

}
