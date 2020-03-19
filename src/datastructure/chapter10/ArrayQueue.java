package datastructure.chapter10;


import java.util.Arrays;

/**
 * 无需计数器的队列类
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    private T[] queue;

    private int frontIndex;

    private int backIndex;

    private boolean initialized = false;

    private static final int DEFAULT_CAPACITY = 16;

    private static final int MAX_CAPACITY = 10000;

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayQueue(int size) {

        if (size > MAX_CAPACITY) {
            throw new RuntimeException("队列长度超过限制");
        }
        //创建长度加1的数组, 很重要, 因为数组有一个空位没有用到, 因此数组实际长度必须是队列长度+1
        queue = (T[]) new Object[size+1];
        frontIndex = 0;
        backIndex = size;
        initialized = true;
    }

    private void checkInitialization() {
        if (!initialized) {
            throw new RuntimeException("队列对象初始化发生错误, 请重新创建队列对象.");
        }
    }



    @Override
    public void enqueue(T entry) {

        checkInitialization();

        if (entry == null) {
            throw new RuntimeException("不允许入队null值");
        }

        //检查容量, 如果小于就不做动作, 大于就扩容, 超过上限则报错. 所以另外编写一个方法
        checkCapacity();

        //能通过检查容量之后, 肯定可以添加, 添加逻辑和之前一样

        backIndex = (backIndex + 1) % queue.length;
        queue[backIndex] = entry;

    }

    @Override
    public T dequeue() {
        checkInitialization();
        if (isEmpty()) {
            throw new RuntimeException("队列已经为空");
        } else {
            T result = queue[frontIndex];
            queue[frontIndex] = null;
            frontIndex = (frontIndex + 1) % queue.length;
            return result;
        }
    }

    @Override
    public T getFront() {
        checkInitialization();
        if (isEmpty()) {
            throw new RuntimeException("队列已经为空");
        } else {
            return queue[frontIndex];
        }
    }

    @Override
    public boolean isEmpty() {
        checkInitialization();
        return frontIndex == (backIndex + 1) % queue.length;
    }

    @Override
    public void clear() {
        checkInitialization();
        while (!isEmpty()) {
            queue[frontIndex] = null;
            frontIndex = (frontIndex + 1) % queue.length;
        }
        frontIndex = 0;
        backIndex = queue.length - 1;
    }

    @SuppressWarnings("unchecked")
    private void checkCapacity() {

        checkInitialization();

        //如果数组是满的, 先看当前容量是不是已经超过上限
        if (frontIndex == (backIndex + 2) % queue.length) {
            if (queue.length == MAX_CAPACITY + 1) {
                throw new RuntimeException("队列无法再扩容");
            }

            //没有超过上限, 比较MAX_CAPACITY+1 与 队列的实际要放的内容的长度乘以2再加1, 哪个小, 就作为新数组的长度.
            T[] newQueue = (T[]) new Object[Math.min((queue.length - 1) * 2 + 1, MAX_CAPACITY + 1)];

            //代码执行到这里, 至少扩容到还可以放下一个元素. 先把原来的数组的所有东西都复制进来, 移动firstIndex直到isEmpty()即可

            int startIndex = 0;
            while (!isEmpty()) {
                newQueue[startIndex] = queue[frontIndex];
                frontIndex = (frontIndex + 1) % queue.length;
                startIndex++;
            }

            queue = newQueue;
            frontIndex = 0;
            backIndex = startIndex - 1;
        }

    }

    public void show() {
        System.out.println(Arrays.toString(queue));
        System.out.print("当前frontIndex = " + frontIndex);
        System.out.print("\t当前backIndex = " + backIndex);
        System.out.println("\t当前length = " + queue.length);
        System.out.println("----------------------------------------------------------------------------------------");

    }

}
