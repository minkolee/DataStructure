package datastructure.chapter1;


public class ArrayBag<T> implements BagInterface<T> {

    private T[] bag;

    private int numberOfEntries;

    private static final int DEFAULT_CAPACITY = 25;

    private boolean initialized;

    private static final int MAX_CAPACITY = 10000;


    @SuppressWarnings("unchecked")
    public ArrayBag(int capacity) {
        if (capacity > MAX_CAPACITY) {
            throw new RuntimeException("Max bag capacity exceeds 10000");
        }

        //因为Object没有实现Comparable类型, 不能进行强制类型转换.
        bag = (T[]) new Object[capacity];
        numberOfEntries = 0;
        initialized = true;
    }


    public ArrayBag() {
        this(DEFAULT_CAPACITY);
    }

    private void checkInitialized() {
        if (!initialized) {
            throw new RuntimeException("Bag is not properly initialized");
        }
    }

    @Override
    public int getCurrnetSize() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean add(T newEntry) {
        checkInitialized();
        boolean isAddSuccess = true;
        if (isArrayFull()) {
            isAddSuccess = false;
        }else {
            bag[numberOfEntries] = newEntry;
            numberOfEntries++;
        }
        return isAddSuccess;
    }

    private boolean isArrayFull() {
        return numberOfEntries >= bag.length;
    }

    @Override
    public void clear() {
        checkInitialized();
        for (int i = 0; i < numberOfEntries; i++) {
            bag[i] = null;
        }
        numberOfEntries = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] result = (T[]) new Object[bag.length];

        for (int index = 0; index < numberOfEntries; index++) {
            result[index] = bag[index];
        }
        return result;
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        int result = 0;
        for (int index = 0; index < numberOfEntries; index++) {
            if (bag[index].equals(anEntry)) {
                result++;
            }
        }
        return result;
    }

    @Override
    public boolean contains(T anEntry) {

        return getIndexOf(anEntry) >= 0;
    }


    @Override
    public T remove() {
        return removeEntry(numberOfEntries - 1);
    }

    @Override
    public boolean remove(T anEntry) {
        int index = getIndexOf(anEntry);
        return removeEntry(index) != null;
    }

    private T removeEntry(int index) {
        checkInitialized();
        if (index == -1 || isEmpty()) {
            return null;
        }

        T result = bag[index];
        bag[index] = bag[numberOfEntries - 1];
        bag[numberOfEntries - 1] = null;
        numberOfEntries--;
        return result;
    }

    private int getIndexOf(T entry) {
        int index = -1;

        for (int i = 0; i < numberOfEntries; i++) {
            if (bag[i].equals(entry)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public T replace(T newEntry, T targetEntry) {
        if (newEntry == null || targetEntry == null) {
            throw new RuntimeException("两个参数都不能为null");
        }
        T result = null;
        int index = getIndexOf(targetEntry);
        if (index > -1) {
            result = bag[index];
            bag[index] = newEntry;
        }
        return result;
    }

}
