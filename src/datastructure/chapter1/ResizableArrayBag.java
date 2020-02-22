package datastructure.chapter1;


import java.util.Arrays;
import java.util.Random;

public class ResizableArrayBag<T extends Comparable<T>> implements BagInterface<T> {

    private T[] bag;

    private int numberOfEntries;

    private static final int DEFAULT_CAPACITY = 25;

    private boolean initialized = false;

    private static final int MAX_CAPACITY = 10000;


    @SuppressWarnings("unchecked")
    public ResizableArrayBag(int capacity) {
        if (capacity > MAX_CAPACITY) {
            throw new RuntimeException("Max bag capacity exceeds 10000");
        }

        bag = (T[]) new Object[capacity];
        numberOfEntries = 0;
        initialized = true;
    }


    public ResizableArrayBag() {
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

    //修改实现, 当数组满的时候不是返回false, 而是增加数组的大小. 可以定义一个私有方法来操作
    @Override
    public boolean add(T newEntry) {
        checkInitialized();
        boolean isAddSuccess = true;
        if (isArrayFull()) {
            doubleCapacity();
        }

        bag[numberOfEntries] = newEntry;
        numberOfEntries++;

        return isAddSuccess;
    }

    private void doubleCapacity() {
        int length = 2 * bag.length;
        checkCapacity(length);
        bag = Arrays.copyOf(bag, length);
    }

    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY) {
            throw new RuntimeException("Cannot Resize Bag because exceeded MAX CAPACITY = 10000");
        }
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

    public T removeRandom() {
        if (!isEmpty()) {
            Random random = new Random();
            return removeEntry(random.nextInt(numberOfEntries));
        }
        return null;
    }

    /**
     * 从包中删除与参数相等的每一个对象, 实现逻辑是
     * 先找到该参数的位置, 然后进行删除, 之后检查被替换的是否与entry相同, 如果不同, 继续下一个位置, 如果相同, 继续删除.
     *
     * @param entry 要删除的对象
     */
    public T removeEvery(T entry) {

        T result = null;

        if (!isEmpty()) {
            for (int i = 0; i < numberOfEntries; i++) {
                //只要能找到, 第一次删除必定成功
                if (bag[i].equals(entry)) {
                    result = removeEntry(i);
                    //有可能删除之后是null, 所以先把判断是不是null放在前边用来短路判断
                    //只要不是null, 又和原来继续相等, 就继续删除当前位置上的元素
                    while (bag[i] != null && bag[i].equals(entry)) {
                        removeEntry(i);
                    }
                }

            }
        }
        return result;
    }


    public int getInternalSize() {
        return bag.length;
    }
}
