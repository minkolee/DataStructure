package datastructure.chapter5;

public interface Stack<T> {

    void push(T newEntry);

    T pop();

    T peek();

    boolean isEmpty();

    void clear();

    T[] toArray();
}
