package alog4e.chapter01.section02.quque;

import alog4e.libs.StdOut;

import java.util.Iterator;

/**
 * 环形链表, 也要使用first 和 last, 本质上没区别. 遍历的时候要注意
 * 实现过了数组, 这个也比较简单, 不容易通过指针判断的时候, 可以引入元素个数来辅助判断
 * @param <T>
 */

public class RingQueue<T> implements Iterable<T> {

    private Node first;
    private Node last;
    private int N;

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    /**
     * 环形链表在插入一个元素的时候, 如果链表原来为空, 就插入一个新元素, first 和 last 都指向新元素, 如果不是空, 就追加一个, 然后将last.next指向frist
     * @param item 元素
     */
    public void enqueue(T item) {
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;
        if (this.isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        last.next = first;
        N++;
    }

    /**
     * 删除的时候就要注意了, 是从first指向的节点删除, 还需要把last.next指向删除后的first
     * 注意如果此时没有节点了, 要把last也更新为null
     * @return 删掉的节点的数据
     */

    public T dequeue() {
        if (this.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        T item = first.item;
        first = first.next;
        N--;
        if (this.isEmpty()) {
            last = null;
        } else{
            last.next = first;
        }
        return item;
    }


    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    //作为内部类实现
    private class LinkedListIterator implements Iterator<T> {
        private Node current = first;
        private Node lastOne = last;
        private int num = N;
        @Override
        public boolean hasNext() {
            return num != 0;
        }

        @Override
        public T next() {
            num--;
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    private class Node {
        T item;
        Node next;
    }

    public static void main(String[] args) {
        RingQueue<String> stack = new RingQueue<>();

        stack.enqueue("saner");
        stack.enqueue("owl");
        stack.enqueue("cony");
        stack.enqueue("kiwi");
        StdOut.println("-------");
        StdOut.println(stack.dequeue());
        StdOut.println(stack.dequeue());
        stack.enqueue("owl2");
        stack.enqueue("choco");
        StdOut.println("-------");
        for (String s : stack) {
            StdOut.println(s);
        }
    }

}

