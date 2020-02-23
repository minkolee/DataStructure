package datastructure.chapter1;


import java.util.Arrays;
import java.util.Random;

public class ResizableArrayBag<T> implements BagInterface<T> {

    protected T[] bag;

    protected int numberOfEntries;

    protected static final int DEFAULT_CAPACITY = 25;

    protected boolean initialized = false;

    protected static final int MAX_CAPACITY = 10000;


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

    protected T removeEntry(int index) {
        checkInitialized();
        if (index == -1 || isEmpty()) {
            return null;
        }

        T result = bag[index];
        bag[index] = bag[numberOfEntries - 1];
        bag[numberOfEntries - 1] = null;
        numberOfEntries--;

        if (isTooBig()) {
            reduceArray();
        }

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

    protected boolean isTooBig() {
        return numberOfEntries < bag.length / 2 && bag.length > 20;
    }

    private void reduceArray() {
        int length = bag.length;
        if (length <= 20) {
            return;
        }
        bag = Arrays.copyOf(bag, length * 3 / 4);
    }

    public ResizableArrayBag<T> union(ResizableArrayBag<T> anotherBag) {
        ResizableArrayBag<T> unionResult = new ResizableArrayBag<>(this.numberOfEntries + anotherBag.numberOfEntries);
        for (int i = 0; i < numberOfEntries; i++) {
            unionResult.add(bag[i]);
        }
        for (int i = 0; i < anotherBag.numberOfEntries; i++) {
            unionResult.add(anotherBag.bag[i]);
        }
        return unionResult;
    }


    public ResizableArrayBag<T> intersection(ResizableArrayBag<T> anotherBag) {
        ResizableArrayBag<T> intersectionResult = new ResizableArrayBag<>();
        for (int i = 0; i < numberOfEntries; i++) {
            int number = anotherBag.getFrequencyOf(bag[i]);
            //只有当前元素也在对面存在, 而且当前元素不存在于结果中的时候才进行操作
            if (number > 0 && !intersectionResult.contains(bag[i])) {
                int thisNumber = this.getFrequencyOf(bag[i]);
                //取两个数量的较小部分 = count
                int count = Math.min(number, thisNumber);
                //然后将当前元素添加count次
                while (count > 0) {
                    intersectionResult.add(bag[i]);
                    count--;
                }
            }
        }

        int length = intersectionResult.getCurrnetSize();

        ResizableArrayBag<T> finalResult = new ResizableArrayBag<>(length);
        for (int i = 0; i < length; i++) {
            finalResult.add(intersectionResult.bag[i]);
        }

        return finalResult;
    }


    public ResizableArrayBag<T> difference(ResizableArrayBag<T> anotherBag) {
        ResizableArrayBag<T> differenceResult = new ResizableArrayBag<>();
        for (int i = 0; i < numberOfEntries; i++) {
            int number = anotherBag.getFrequencyOf(bag[i]);
//            System.out.println("当前的元素是: " + bag[i]);
            if (!differenceResult.contains(bag[i])) {
                int thisNumber = this.getFrequencyOf(bag[i]);
                //根据两个数量来得到结果 count, 如果自己的数量比其大, 就添加差额, 如果相等或者小于0, 就不添加.
                int count = Math.max(thisNumber - number, 0);
                //然后将当前元素添加count次
//                System.out.println("当前的值是: " + bag[i] + " 在自己包中出现几次: " + thisNumber + " 在另外一个包内出现几次: " + number + "要添加几次: " + count);
                while (count > 0) {
                    differenceResult.add(bag[i]);
                    count--;
                }
            }
        }

        int length = differenceResult.getCurrnetSize();

        ResizableArrayBag<T> finalResult = new ResizableArrayBag<>(length);
        for (int i = 0; i < length; i++) {
            finalResult.add(differenceResult.bag[i]);
        }

        return finalResult;
    }

}
