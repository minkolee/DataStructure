package alog4e.chapter01.section02.quque;

import alog4e.libs.StdOut;

import java.util.Iterator;


public class Queue<T> implements Iterable<T> {

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
     * 入列将元素插入到链表的最后, 如果本来N=0 即链表为空, 则将头尾节点都指向newNode
     * 如果不为空, 仅操作 last 即可
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
        N++;
    }

    /**
     * 从链表头部删除节点, 只要将first指向首节点的next节点即可.
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
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
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
        Queue<String> stack = new Queue<>();

        stack.enqueue("saner");
        stack.enqueue("owl");
        stack.enqueue("cony");
        stack.enqueue("kiwi");
        StdOut.println("-------");
        StdOut.println(stack.dequeue());
        StdOut.println(stack.dequeue());
        StdOut.println("-------");
        for (String s : stack) {
            StdOut.println(s);
        }
    }

}

