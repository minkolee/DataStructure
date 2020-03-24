package datastructure.chapter10;

public interface PriorityQueueInterface<T> {

    void add(T entry);

    T remove();

    T peek();

    boolean isEmpty();

    int getSize();

    void clear();

}

