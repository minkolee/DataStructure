package datastructure.chapter1;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class ResizableArrayBagComparable<T> extends ResizableArrayBag<T> {

    public ResizableArrayBagComparable(int size) {
        super(size);
    }

    public ResizableArrayBagComparable() {
        super();
    }

    public T getMin(Comparator<T> comparator) {
        T result = null;
        int index = getMinIndex(comparator);
        if (index >= 0) {
            result = bag[index];
        }
        return result;
    }

    public T getMax(Comparator<T> comparator) {
        T result = null;
        int index = getMaxIndex(comparator);
        if (index >= 0) {
            result = bag[index];
        }
        return result;

    }

    private int getMinIndex(Comparator<T> comparator) {
        if (isEmpty()) {
            return -1;
        }

        int result = 0;
        T min = bag[0];
        for (int i = 0; i < numberOfEntries; i++) {
            if (comparator.compare(min, bag[i]) > 0) {
                min = bag[i];
                result = i;
            }
        }
        return result;
    }

    private int getMaxIndex(Comparator<T> comparator) {
        if (isEmpty()) {
            return -1;
        }

        int result = 0;
        T max = bag[0];
        for (int i = 0; i < numberOfEntries; i++) {
            if (comparator.compare(max, bag[i]) < 0) {
                max = bag[i];
                result = i;
            }
        }
        return result;
    }

    public T removeMin(Comparator<T> comparator) {
        int index = getMinIndex(comparator);
        if (index < 0) {
            return null;
        }
        T result = bag[index];
        removeEntry(index);
        return result;
    }

    public T removeMax(Comparator<T> comparator) {
        int index = getMaxIndex(comparator);
        if (index < 0) {
            return null;
        }
        T result = bag[index];
        removeEntry(index);
        return result;
    }

    public BagInterface<T> getAllLessThan(T entry, Comparator<T> comparator) {
        BagInterface<T> result = new ResizableArrayBag<>();
        for (int i = 0; i < numberOfEntries; i++) {
            if (comparator.compare(bag[i], entry) < 0) {
                result.add(bag[i]);
            }
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if (this.numberOfEntries != ((ResizableArrayBagComparable<T>) obj).getCurrnetSize()) {
            return false;
        }

        for (int i = 0; i < numberOfEntries; i++) {
            if (this.getFrequencyOf(bag[i]) != ((ResizableArrayBagComparable<T>) obj).getFrequencyOf(bag[i])) {
                return false;
            }
        }
        return true;
    }





}
