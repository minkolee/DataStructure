package datastructure.chapter23;

import datastructure.chapter23.BinaryTreeInterface;
import datastructure.chapter23.TreeInterface;

import java.util.Iterator;

public interface BinarySearchTreeInterface<T extends Comparable<? super T>> extends TreeInterface<T>, TreeIterator<T> {

    public boolean contains(T entry);

    public T getEntry(T entry);

    public T add(T newEntry);

    public T remove(T entry);

    Iterator<T> iterator();
}
