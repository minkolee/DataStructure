package datastructure.chapter12;


import java.util.Iterator;

public class MyLinkedList<T> implements ListInterface<T>, Iterable<T> {

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>();
    }

    private class LinkedListIterator<T> implements Iterator<T> {

        private Node currentNode;

        private LinkedListIterator() {
            this.currentNode = firstNode;
        }

        @Override
        public boolean hasNext() {

            return currentNode != null;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            T result = (T) currentNode.data;
            currentNode = currentNode.next;
            return result;
        }
    }









    private class Node {
        private T data;
        private Node next;

        public Node(T data) {
            this(data, null);
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    private Node firstNode;
    private Node lastNode;
    private int numberOfEntries;

    public MyLinkedList() {
        initialize();
    }

    private void initialize() {
        firstNode = null;
        lastNode = null;
        numberOfEntries = 0;
    }

    @Override
    public void add(T entry) {
        //创建新节点
        Node newNode = new Node(entry);

        //如果线性表为空,要添加的节点同时是头节点和尾节点
        if (isEmpty()) {
            firstNode = newNode;
            lastNode = newNode;
        //如果不为空, lastNode一定不为空, 添加在尾部
        } else {
            lastNode.next = newNode;
            lastNode = lastNode.next;
        }
        numberOfEntries++;
    }

    @Override
    public void add(int newPosition, T newEntry) {
        //添加在指定位置,显然需要先检查索引.
        //思考最简单的例子,一个长度1的链表, 只可能添加在0或者1号位置, 所以newPosition <= numberOfEntries
        if (newPosition > numberOfEntries) {
            throw new RuntimeException("插入位置的索引不合法: " + newPosition);
        }

        //然后需要从头开始遍历链表, 遍历到要插入的位置之前一个位置

        //这里需要考虑两种情况, 如果从0号位置插入,表示插入在链表头部. 如果从nubmerOfEntries位置插入, 表示从尾部插入, 剩下的就需要手工拼接一下

        //0号位置插入的时候如果数组为空, 和普通插入一样
        if (newPosition == 0) {
            if (isEmpty()) {
                add(newEntry);
            //如果不为空, 则就是在链表头部添加节点
            } else {
                Node newNode = new Node(newEntry);
                newNode.next = firstNode;
                firstNode = newNode;
                numberOfEntries++;
            }

        //如果索引和当前的元素数量相等,说明是从尾部插入, 也调用add方法即可
        } else if (newPosition == numberOfEntries) {
            add(newEntry);

        //剩下的情况表示不是头也不是尾,那么就需要找到要插入的节点的前一个元素.
        //简单分析一下,如果数组只有一个元素,插入点不是0就是1,会掉入上边两种情况.
        //如果数组长度为2,符合条件的是1,在1号位置插入需要获取0号位置的节点. 如果数组长度是3,符合条件的是1,2号位置, 要获取的节点位置是0,1 所以可见,从开头循环到newPosition - 1的位置即可
        } else {
            Node currentNode = firstNode;
            for (int i = 0; i < newPosition - 1; i++) {
                currentNode = currentNode.next;
            }
            //循环结束后, currentNode指向要插入的位置的上一个节点
            Node newNode = new Node(newEntry);
            newNode.next = currentNode.next;
            currentNode.next = newNode;
            numberOfEntries++;
        }

    }

    @Override
    public T remove(int givenPosition) {
        T result = null;
        //像检查数组索引一样检查给出的位置是否正确.
        if (givenPosition < 0 || givenPosition > numberOfEntries - 1) {
            throw new RuntimeException("指定的索引超出范围");
        }

        //然后需要处理几个特例: 线性表长度为1的情况; 删除链表最后一个位置上的元素的情况

        //如果删除线性表的唯一一个元素, 则直接清除即可
        if (numberOfEntries == 1 && givenPosition == 0) {
            result = firstNode.data;
            initialize();
            return result;
        }

        //numberOfEntries不为1的情况下, 删除第一个节点
        if (givenPosition == 0) {
            result = firstNode.data;
            firstNode = firstNode.next;
            numberOfEntries--;
            return result;
        }

        //如果删除的是线性表的最后一个元素, 需要获取到上一个元素, 然后记得要把lastNode更改掉
        if (givenPosition == numberOfEntries - 1) {
            Node currentNode = firstNode;

            while (currentNode.next != lastNode) {
                currentNode = currentNode.next;
            }
            //循环结束之后, currentNode.next = lastNode, 即currentNode是lastNode的前一个节点

            result = lastNode.data;
            currentNode.next = null;
            lastNode = currentNode;
            numberOfEntries--;
            return result;

        //这种情况就是numberOfEntries大于1, 而且又没有删除尾节点的情况, 依然从头开始遍历到索引-1的位置, 就获取了上一个节点.
        } else {
            Node currentNode = firstNode;
            for (int i = 0; i < givenPosition - 1; i++) {
                currentNode = currentNode.next;
            }
            //循环结束之后, currentNode指向要删除的节点的上一个节点
            result = currentNode.next.data;
            currentNode.next = currentNode.next.next;
            numberOfEntries--;
            return result;
        }

    }

    @Override
    public void clear() {
        initialize();
    }

    @Override
    public T replace(int givenPosition, T newEntry) {
        T result = null;
        //检查位置的正确性
        if (givenPosition < 0 || givenPosition > numberOfEntries - 1) {
            throw new RuntimeException("指定的索引超出范围");
        }

        Node currentNode = firstNode;

        for (int i = 0; i < givenPosition; i++) {
            currentNode = currentNode.next;
        }

        result = currentNode.data;
        currentNode.data = newEntry;
        return result;
    }

    @Override
    public T getEntry(int givenPosition) {
        T result;

        //检查位置的正确性
        if (givenPosition < 0 || givenPosition > numberOfEntries - 1) {
            throw new RuntimeException("指定的索引超出范围");
        }

        Node currentNode = firstNode;

        for (int i = 0; i < givenPosition; i++) {
            currentNode = currentNode.next;
        }

        result = currentNode.data;
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {

        T[] result = (T[]) new Object[numberOfEntries];
        Node currentNode = firstNode;
        for (int i = 0; i < numberOfEntries; i++) {

            result[i] = currentNode.data;
            currentNode = currentNode.next;
        }
        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        Node currentNode = firstNode;
        while (currentNode != null) {
            if (currentNode.data.equals(anEntry)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public int getLength() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0 && firstNode == null && lastNode == null;
    }
}
