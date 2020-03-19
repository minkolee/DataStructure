package datastructure.chapter10;



/**
 * 无需计数器的队列类
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    private T[] queue;

    private int frontIndex;

    private int backIndex;

    private boolean initialized = false;

    private static final int DEFAULT_CAPACITY = 50;

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

    private void checkCapacity() {

    }

}
