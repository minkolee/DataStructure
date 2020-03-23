package datastructure.chapter10;

public interface DequeInterface<T> {

    void addToFront(T entry);
    void addToBack(T entry);

    T removeFront();
    T removeBack();

    T getFront();
    T getBack();

    boolean isEmpty();

    void clear();

}
