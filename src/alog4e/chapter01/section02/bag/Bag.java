package alog4e.chapter01.section02.bag;

import alog4e.libs.StdOut;

import java.util.Iterator;

/**
 * 用栈修改而来的背包
 * @param <T> 泛型类型
 *
 */
public class Bag<T> implements Iterable<T> {
    /**
     * first: 指向最后一个压入栈的节点
     * N: 为了方便操作, 还是保存一个N做为元素数量
     */
    private Node first;
    private int N;

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    /**
     * 从链表头部插入节点
     * @param item 元素
     */
    public void add(T item) {
        Node oldfirst = first;
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = oldfirst;
        first = newNode;
        N++;
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
        Bag<String> stack = new Bag<>();
        stack.add("saner");
        stack.add("owl");
        stack.add("cony");
        stack.add("kiwi");
        StdOut.println("-------");
        StdOut.println("-------");
        for (String s : stack) {
            StdOut.println(s);
        }
    }

}

