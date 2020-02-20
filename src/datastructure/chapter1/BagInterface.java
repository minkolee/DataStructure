package datastructure.chapter1;

public interface BagInterface<T> {

    int getCurrnetSize();

    boolean isEmpty();

    boolean add(T newEntry);

    T remove();

    boolean remove(T anEntry);

    void clear();

    int getFrequencyOf(T anEntry);

    boolean contains(T anEntry);

    T[] toArray();

}
