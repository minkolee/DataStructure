package alog4e.chapter01.section02.stack;

import java.util.Iterator;

public class ResizingArrayStack<T> implements Iterable<T> {
    private T[] stack;
    private int N;

    public ResizingArrayStack(int cap) {
        stack = (T[]) (new Object[cap]);
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(T item) {
        if (N == stack.length) {
            resize(stack.length * 2);
        }
        stack[N++] = item;
    }

    public T pop() {
        T item = stack[--N];

        //这里注意, 设置成null, 释放内存
        stack[N] = null;
        if (N>0 && N == stack.length / 4) {
            resize(stack.length / 2);
        }
        return item;
    }

    public void resize(int max) {
        T[] temp = (T[]) (new Object[max]);
        for (int i = 0; i < temp.length; i++) {
            temp[i] = stack[i];
        }
        stack = temp;
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

    }

}

