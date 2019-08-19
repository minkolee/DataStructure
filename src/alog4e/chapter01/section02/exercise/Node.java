package alog4e.chapter01.section02.exercise;

public class Node<T> {
    T item;
    Node<T> next;

    @Override
    public String toString() {
        return "Node{" +
                "item=" + item +
                '}';
    }
}
