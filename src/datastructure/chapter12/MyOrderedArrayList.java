package datastructure.chapter12;

import java.util.Iterator;

public class MyOrderedArrayList<T extends Comparable<? super T>> implements OrderedListInterface<T>, Iterable<T> {

    @Override
    public void add(T entry) {

        boolean found = false;

        int currentIndex = 0;

        //数组本来为空则currentIndexy一开始就等于numberOfEntries, 逻辑也是对的
        while (!found) {
            //如果currentIndex 已经等于元素数量, 就break
            if (currentIndex == numberOfEntries) {
                break;
            }


            //要插入元素大于等于数组某个位置的元素, 向后移动currentIndex++
            if (entry.compareTo(list[currentIndex]) >= 0) {
                currentIndex++;
            } else {
                found = true;
            }
        }

        //循环结束之后, currentIndex要么在末尾, 要么就在第一个大于要插入的元素的位置, 也就是需要将元素插入的位置

        //调用私有方法在指定位置插入即可
        add(currentIndex, entry);
    }

    @Override
    public boolean remove(T anEntry) {
        int index = getPosition(anEntry);

        if (index < 0) {
            return false;
        } else {
            remove(index);
            return true;
        }
    }

    @Override
    public int getPosition(T anEntry) {

        int firstIndex = 0;

        int lastIndex = numberOfEntries - 1;

        while (true) {
            if (firstIndex > lastIndex) {
                return -1;
            }

            int middle = (firstIndex + lastIndex) / 2;
            if (list[middle].compareTo(anEntry) == 0) {
                return middle;
            } else if (list[middle].compareTo(anEntry) < 0) {
                firstIndex = middle + 1;
            } else {
                lastIndex = middle - 1;
            }

        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator<>();
    }

    private class ArrayListIterator<T> implements Iterator<T> {

        private int number;

        private int index = 0;

        private ArrayListIterator() {
            this.number = numberOfEntries;
        }

        @Override
        public boolean hasNext() {
            return index <= number - 1;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            T result = (T) list[index];
            index++;
            return result;
        }
    }


    //内部数组
    private T[] list;

    private int numberOfEntries = 0;

    private boolean initialized;

    private static int DEFAULT_CAPACITY = 16;

    private static int MAX_CAPACITY = 10000;

    private void checkInitialized() {
        if (!initialized) {
            throw new RuntimeException("类没有初始化成功.");
        }
    }

    //根据传入的初始长度创建内部数组, 最小不短于默认长度
    @SuppressWarnings("unchecked")
    public MyOrderedArrayList(int size) {
        if (size > MAX_CAPACITY) {
            throw new RuntimeException("超过限制");
        } else if (size < DEFAULT_CAPACITY) {
            size = DEFAULT_CAPACITY;
        }
        System.out.println("创建成功长度为: " + size + " 的线性表");
        list = (T[]) new Comparable[size];
        initialized = true;
    }

    public MyOrderedArrayList() {
        this(DEFAULT_CAPACITY);
    }


    private void add(int newPosition, T newEntry) {

        if (newPosition > numberOfEntries) {
            throw new RuntimeException("插入位置的索引不合法: " + newPosition);
        }


        if (isFull()) {
            enlargeCapacity();
        }
        //已经知道扩展后的数组必定至少还能放下一个元素. numberOfEntries指向的是数组最后一个元素之后的空白位置, 从这里反向往前循环即可.
        int currentIndex = numberOfEntries;
        while (currentIndex != newPosition) {
            list[currentIndex] = list[currentIndex - 1];
            currentIndex--;
        }
        list[newPosition] = newEntry;
        numberOfEntries++;
    }

    @Override
    public T remove(int givenPosition) {
        checkInitialized();

        //检查指定的索引是否存在, 当有n个元素的时候, 最小只能是0, 最大的范围是n-1
        if (givenPosition < 0 || givenPosition > numberOfEntries - 1) {
            throw new RuntimeException("指定的索引超出范围");
        }

        T result = list[givenPosition];

        //从指定的索引开始, 将之后的都往前移动一个位置
        int currentIndex = givenPosition;
        while (currentIndex != numberOfEntries - 1) {
            list[currentIndex] = list[currentIndex + 1];
            currentIndex++;
        }

        numberOfEntries--;
        return result;
    }

    @Override
    public void clear() {
        //clear只需要将numberOfEntries设置为1即可,无论是控制放入, 还是返回内部数组, 都是根据numberOfEntries来控制的. 不过最好还是将所有的位置都释放掉

        for (int i = 0; i < numberOfEntries; i++) {
            list[i] = null;
        }
        numberOfEntries = 0;
    }

    @Override
    public T replace(int givenPosition, T newEntry) {
        checkInitialized();
        T result = list[givenPosition];
        list[givenPosition] = newEntry;
        return result;
    }

    @Override
    public T getEntry(int givenPosition) {
        checkInitialized();
        return list[givenPosition];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        checkInitialized();
        T[] result = (T[]) new Comparable[numberOfEntries];

        System.arraycopy(list, 0, result, 0, numberOfEntries);

        return result;
    }

    @Override
    public boolean contains(T anEntry) {

        int firstIndex = 0;

        int lastIndex = numberOfEntries - 1;

        while (true) {
            if (firstIndex > lastIndex) {
                return false;
            }

            int middle = (firstIndex + lastIndex) / 2;
            if (list[middle].compareTo(anEntry) == 0) {
                return true;
            } else if (list[middle].compareTo(anEntry) < 0) {
                firstIndex = middle + 1;
            } else {
                lastIndex = middle - 1;
            }

        }
    }

    @Override
    public int getLength() {
        checkInitialized();
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        checkInitialized();
        return numberOfEntries == 0;
    }

    private boolean isFull() {
        checkInitialized();
        return numberOfEntries == list.length;
    }

    @SuppressWarnings("unchecked")
    private void enlargeCapacity() {
        int currentNumber = list.length;

        if (currentNumber == MAX_CAPACITY) {
            throw new RuntimeException("线性表无法继续扩大");
        } else {
            //创建一个最长不超过MAX_CAPACITY的数组, 然后将数组复制过去
            T[] tempArray = (T[]) new Comparable[Math.min(currentNumber * 2, MAX_CAPACITY)];
            if (numberOfEntries >= 0) System.arraycopy(list, 0, tempArray, 0, numberOfEntries);
            list = tempArray;
        }
    }

}

