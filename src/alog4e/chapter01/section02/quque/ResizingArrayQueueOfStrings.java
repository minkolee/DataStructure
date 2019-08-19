package alog4e.chapter01.section02.quque;

import alog4e.libs.StdOut;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 使用定长数组的队列实现.
 * 先编写定长, 再进行修改
 */

public class ResizingArrayQueueOfStrings implements Iterable<String> {
    //原始数组的长度
    private int size;
    //放入元素的个数
    private int N;
    //队列的开头索引
    private int head;
    //队列的结尾索引,指向可插入的位置
    private int tail;

    private String[] queue;

    ResizingArrayQueueOfStrings(int size) {
        this.size = size;
        queue = new String[size];
    }

    /**
     * 入队列前先判断是不是满了, 元素个数等于数组长度, 肯定就满了
     * 放入元素的时候, 移动tail指向的位置, 如果tail等于了数组的长度, 即在size-1的位置插入了新元素, tail就变成0, 即从头再开始放
     *
     * @param s 元素
     */
    public void enqueue(String s) {
        //数组已满就加倍
        if (N == size) {
            resize(size * 2);
        }

        queue[tail++] = s;
        if (tail == size) {
            tail = 0;
        }
        //放入元素的数量加1
        N++;
    }

    /**
     * 弹出新元素的时候, 从head指向的地方弹, 第一个元素肯定是插入在队列头, 所以先弹第一个, 然后也要移动head
     * 与tail一样, head如果超过了数组索引, 也需要将其重置为0
     */
    public String dequeue() {
        if (N == 0) {
            throw new RuntimeException("Queue is already empty when dequeue.");
        }

        String s = queue[head++];
        if (head == size) {
            head = 0;
        }
        N--;
        //N小于size的四分之一, 就缩小数组长度
        if (N < size / 4) {
            resize(size / 2);
        }
        return s;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    /**
     * 扩展成可变数组的关键是这个函数
     * 先创建一个新的数组, 然后从0 到 size-1, 将原来的数组按照head开始复制到tail, 复制到新数组的0-N-1的索引位置, 然后更新size ,head ,tail
     * @param max 新的数组的长度
     */
    private void resize(int max) {
        //创建新数组
        String[] newqueue = new String[max];

        for (int i = 0; i < this.N; i++) {
            newqueue[i] = this.queue[(head + i) % size];
        }
        //复制完成之后,重新设置size, head, tail 和新的内部数组
        this.size = max;
        this.head = 0;
        //注意tail的指向, 是下一个空白区域, resize执行过程中不会N肯定会小于size,不会出现溢出
        this.tail = this.N;
        this.queue = newqueue;
    }

    public int length() {
        return this.size;
    }


    @Override
    public Iterator<String> iterator() {
        return new ArrayQueueIterator();
    }

    private class ArrayQueueIterator implements Iterator<String> {
        private int first = head;
        private int number = N;
        private int length = size;
        @Override
        public boolean hasNext() {
            return number != 0;
        }

        /**
         * next 函数要注意, 从head 的位置开始遍历取数, 如果超过了长度怎么办, 索引就是去除以size取余
         * @return 是否还有下一个
         */
        @Override
        public String next() {
            String result = queue[first];
            first = (first + 1) % length;
            number--;
            return result;
        }
    }

    @Override
    public String toString() {
        return "ResizingArrayQueueOfStrings{" +
                "size=" + size +
                ", N=" + N +
                ", head=" + head +
                ", tail=" + tail +
                ", queue=" + Arrays.toString(queue) +
                '}';
    }

    public static void main(String[] args) {
        ResizingArrayQueueOfStrings re = new ResizingArrayQueueOfStrings(6);
        StdOut.println("数组长度为6");
        re.enqueue("cony1");
        re.enqueue("cony2");
        re.enqueue("cony3");
        re.enqueue("cony4");
        re.enqueue("cony5");
        re.enqueue("cony6");
        StdOut.println(re);
        StdOut.println("-----------");
        StdOut.println("数组扩容到12");
        re.enqueue("cony7");
        re.enqueue("cony8");
        re.enqueue("cony9");
        re.enqueue("cony10");
        re.enqueue("cony11");
        re.enqueue("cony12");
        StdOut.println(re);

        StdOut.println("-----------");
        StdOut.println("数组缩到12的一半6个长");

        StdOut.println(re);

        StdOut.println("-----------");
        StdOut.println("再尝试添加和删除");
        re.enqueue("cony13");
        re.enqueue("cony14");
        re.enqueue("cony15");
        re.enqueue("cony16");
        re.enqueue("cony17");
        StdOut.println(re);
    }

}
