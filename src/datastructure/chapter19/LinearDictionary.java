package datastructure.chapter19;

import datastructure.chapter12.MyArrayList;

import java.util.Iterator;
import java.util.Objects;

public class LinearDictionary<K, V> implements Dictionary<K, V> {

    private MyArrayList<Entry<K, V>> dictionary;

    private static int DEFAULT_CAPACITY = 16;

    private static int MAX_CAPACITY = 10000;

    public LinearDictionary() {
        dictionary = new MyArrayList<>(DEFAULT_CAPACITY);
    }

    public LinearDictionary(int size) {
        dictionary = new MyArrayList<>(size);
    }


    private class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return key.equals(entry.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }










    @Override
    public void add(K key, V value) {

        if (key == null || value == null) {
            throw new RuntimeException("不允许添加null.");
        }

        Entry<K, V> newEntry = new Entry<>(key, value);

        int index = dictionary.getPosition(newEntry);

        if (index == -1) {
            dictionary.add(newEntry);
        } else {
            dictionary.replace(index, newEntry);
        }

    }

    @Override
    public V remove(K key) {

        int index = getIndex(key);
        if (index == -1) {
            return null;
        } else {
            return dictionary.remove(index).value;
        }
    }

    @Override
    public V getValue(K key) {
        int index = getIndex(key);
        if (index == -1) {
            return null;
        } else {
            return dictionary.getEntry(index).value;
        }
    }

    @Override
    public boolean contains(K key) {
        return getIndex(key) != -1;
    }

    @Override
    public Iterator<K> getKeyIterator() {
        return new KeyIterator();
    }

    @Override
    public Iterator<V> getValueIterator() {
        return new ValueIterator();
    }

    @Override
    public boolean isEmpty() {
        return dictionary.getLength() == 0;
    }

    @Override
    public int getSize() {
        return dictionary.getLength();
    }

    @Override
    public void clear() {
        dictionary.clear();
    }

    @SuppressWarnings("unchecked")
    private int getIndex(K key) {
        return dictionary.getPosition(new Entry<K, V>(key, (V) new Object()));
    }


    public void showAll() {
        for (int i = 0; i < dictionary.getLength(); i++) {
            System.out.println(dictionary.getEntry(i));
        }
    }

    private class KeyIterator implements Iterator<K>{

        private int index;

        private int number;

        KeyIterator() {
            this.index = 0;
            this.number = getSize();
        }

        @Override
        public boolean hasNext() {
            return index != number;
        }

        @Override
        public K next() {
            K result = dictionary.getEntry(index).key;
            index++;
            return result;
        }
    }

    private class ValueIterator implements Iterator<V>{

        private int index;

        private int number;

        ValueIterator() {
            this.index = 0;
            this.number = getSize();
        }

        @Override
        public boolean hasNext() {
            return index != number;
        }

        @Override
        public V next() {
            V result = dictionary.getEntry(index).value;
            index++;
            return result;
        }
    }













}


