package datastructure.chapter23;

public interface TreeInterface<T>  {

    T getRootData();

    int getHeight();

    int getNumberOfNodes();

    boolean isEmpty();

    void clear();
}
