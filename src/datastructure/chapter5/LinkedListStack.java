package datastructure.chapter5;

import datastructure.chapter2.LinkedBag;

public class LinkedListStack<T> implements Stack<T> {

    private class Node {
        private T data;
        private Node next;

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode) {
            this.data = dataPortion;
            this.next = nextNode;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    private Node firstNode;
    private int numberOfEntries;

    public LinkedListStack() {
        this.firstNode = null;
        this.numberOfEntries = 0;
    }


    @Override
    public T pop() {
        return remove();
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空, 不能进行peek()操作");
        } else {
            return firstNode.data;
        }
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] result = (T[]) new Object[numberOfEntries];
        int i = 0;
        Node currentNode = firstNode;
        while (i < numberOfEntries && currentNode != null) {
            result[i++] = currentNode.data;
            currentNode = currentNode.next;
        }
        return result;
    }

    @Override
    public void push(T newEntry) {
        add(newEntry);
    }

    /**
     * 私有方法删除并返回一个数据, 删除链表开头的数据
     * 如果链表为空则抛出异常
     *
     * @return 当链表为空返回null, 不为空则返回链表第一个节点的数据对象
     */
    private T remove() {
        T result = null;
        if (!isEmpty()) {
            result = firstNode.data;
            firstNode = firstNode.next;
            numberOfEntries--;
        } else {
            throw new RuntimeException("栈为空, 不能进行pop()操作");
        }
        return result;
    }

    /**
     * 向链表中添加一个新的数据, 原理是创建一个新节点, 让新节点指向链表头, 然后让链表头指向新节点
     *
     * @param newEntry The object to be added as a new entry.
     */
    private void add(T newEntry) {
        Node newNode = new Node(newEntry);
        newNode.next = firstNode;
        firstNode = newNode;
        numberOfEntries++;
    }
}
