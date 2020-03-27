package datastructure.chapter12;

public interface OrderedListInterface<T extends Comparable<? super T>> {

    //在末尾添加元素
    void add(T entry);

    //删除某个元素
    boolean remove(T anEntry);

    //查找元素的位置
    int getPosition(T anEntry);

    //删除某个位置的元素
    T remove(int givenPosition);

    //清空表
    void clear();

    //替换指定位置的元素
    T replace(int givenPosition, T newEntry);

    //获取指定位置的元素
    T getEntry(int givenPosition);

    //获取全部元素
    T[] toArray();

    //是否含有某个元素
    boolean contains(T anEntry);

    //获取其中的元素数量
    int getLength();

    //是否为空
    boolean isEmpty();
}
