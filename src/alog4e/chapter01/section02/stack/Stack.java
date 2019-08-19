package alog4e.chapter01.section02.stack;

import alog4e.libs.StdOut;

import java.util.Iterator;

/**
 * 构造器不需要具体长度了, 每次构造的时候返回一个first指向null的栈, 符合空栈的要求
 * 我们的栈为了避免遍历链表, 压栈是将元素插入头节点, 弹栈是删除头节点
 * @param <T> 泛型类型
 *
 */
public class Stack<T> implements Iterable<T> {
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
    public void push(T item) {
        Node oldfirst = first;
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = oldfirst;
        first = newNode;
        N++;
    }

    /**
     * 从链表头部删除节点, 只要将first指向首节点的next节点即可.
     * @return 删掉的节点的数据
     */

    public T pop() {
        if (this.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        T item = first.item;
        first = first.next;
        N--;
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
        Stack<String> stack = new Stack<>();

        stack.push("saner");
        stack.push("owl");
        stack.push("cony");
        stack.push("kiwi");
        StdOut.println("-------");
        StdOut.println(stack.pop());
        StdOut.println(stack.pop());
        StdOut.println(stack.pop());
        StdOut.println(stack.pop());
        StdOut.println("-------");
        for (String s : stack) {
            StdOut.println(s);
        }
    }

}

