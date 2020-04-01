package datastructure.chapter19;

import java.util.Iterator;

public interface Dictionary<K, V> {

    void add(K key, V value);

    V remove(K key);

    V getValue(K key);

    boolean contains(K key);

    Iterator<K> getKeyIterator();

    Iterator<V> getValueIterator();

    boolean isEmpty();

    int getSize();

    void clear();

}
