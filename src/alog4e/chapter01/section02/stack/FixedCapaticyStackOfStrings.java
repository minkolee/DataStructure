package alog4e.chapter01.section02.stack;

import alog4e.libs.StdOut;

import java.util.Iterator;

public class FixedCapaticyStackOfStrings<T> implements Iterable<T> {
    private T[] stack;
    private int N;

    public FixedCapaticyStackOfStrings(int cap) {
        stack = (T[]) (new Object[cap]);
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(T item) {
        if (this.isFull()) {
            throw new RuntimeException("Stack overflow");
        }
        stack[N++] = item;
    }

    public T pop() {
        return stack[--N];
    }

    //添加了isFull方法之后, push函数就可以判断是不是队列已满, 满了就抛运行时错误
    public boolean isFull() {
        return N == stack.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    //作为内部类实现
    private class ReverseArrayIterator implements Iterator<T> {
        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return stack[--i];
        }
    }

    public static void main(String[] args) {
        FixedCapaticyStackOfStrings<String> stack = new FixedCapaticyStackOfStrings<>(5);
        stack.push("cony");
        stack.push("guggu");
        stack.push("owl");
        stack.push("choco");
        StdOut.println(stack.isFull());
        stack.push("late");
        StdOut.println(stack.isFull());
        stack.push("kiwi");
    }

}

