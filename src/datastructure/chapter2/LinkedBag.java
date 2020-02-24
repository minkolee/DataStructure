package datastructure.chapter2;

import datastructure.chapter1.ResizableArrayBag;

import java.util.Comparator;
import java.util.Objects;
import java.util.Random;

public class LinkedBag<T> implements BagInterface<T> {

    private class Node {
        private T data;
        private Node next;

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode) {
            this.data = dataPortion;
            this.next = nextNode;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    private Node firstNode;
    private int numberOfEntries;

    public LinkedBag() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0 && firstNode == null;
    }

    /**
     * 向链表中添加一个新的数据, 原理是创建一个新节点, 让新节点指向链表头, 然后让链表头指向新节点
     * @param newEntry  The object to be added as a new entry.
     * @return 必定返回true, 如果因为内存用光, 无需从内存错误中恢复
     */
    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);
        newNode.next = firstNode;
        firstNode = newNode;
        numberOfEntries++;
        return true;
    }

    @Override
    public T remove() {
        T result = null;
        if (!isEmpty()) {
            result = firstNode.data;
            firstNode = firstNode.next;
            numberOfEntries--;
        }
        return null;
    }

    @Override
    public boolean remove(T anEntry) {
        boolean result = false;
        //表不为空
        if (!isEmpty()) {
            //获取节点引用
            Node target = getRefference(anEntry);
            //如果有该节点再进行删除操作
            if (target != null) {
                target.data = firstNode.data;
                remove();
                result = true;
            }
        }
        return result;
    }

    @Override
    public void clear() {
        numberOfEntries = 0;
        firstNode = null;
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        int count = 0;
        int index = 0;
        Node currentNode = firstNode;

        while (currentNode != null && (index < numberOfEntries)) {
            if (anEntry.equals(currentNode.data)) {
                count++;
            }
            currentNode = currentNode.next;
            index++;
        }
        return count;
    }

    @Override
    public boolean contains(T anEntry) {
        return getRefference(anEntry) != null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] result = (T[]) new Object[numberOfEntries];
        int i = 0;
        Node currentNode = firstNode;
        while (i < numberOfEntries && currentNode != null){
            result[i++] = currentNode.data;
            currentNode = currentNode.next;
        }
        return result;
    }

    private Node getRefference(T entry) {
        int index = 0;
        Node currentNode = firstNode;
        while (currentNode != null && (index < numberOfEntries)) {
            if (entry.equals(currentNode.data)) {
                break;
            }
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    public LinkedBag(T[] array) {
        this();
        int length = array.length;
        for (T t : array) {
            add(t);
        }
    }

    //练习3
    public T replace(T newEntry, T oldEntry) {
        T result = null;

        //直接尝试获取oldEntry的引用
        Node target = getRefference(oldEntry);
        if (target != null) {
            result = target.data;
            target.data = newEntry;
        }
        return result;
    }

    public T removeRandom() {
        T result = null;
        //原理就是随机控制遍历的一个数字, 然后删除那个位置的节点即可. 由于手工控制遍历的点了, 因此就获取了引用, 再调用删除指定节点的方法即可
        if (isEmpty()) {
            return result;
        }
        Random random = new Random();
        int index = random.nextInt(numberOfEntries);
        Node currentNode = firstNode;
        //定位到随机节点
        while (index > 0 && currentNode != null) {
            index--;
            currentNode = currentNode.next;
        }
        //已经获取到对Node节点的引用. 直接删除之,无需再去调用getReference方法了

        if (currentNode != null) {
            result = currentNode.data;
            currentNode.data = firstNode.data;
            remove();
        }
        return result;
    }

    public boolean removeEvery(T entry) {
        boolean deleted = false;
        Node currentNode = firstNode;

        while (currentNode != null) {
            if (currentNode.data.equals(entry)) {
                currentNode.data = firstNode.data;
                currentNode = currentNode.next;
                remove();
                deleted = true;
            } else {
                currentNode = currentNode.next;
            }
        }
        return deleted;
    }

    private Node getMinRefference(Comparator<T> comparator) {
        if (isEmpty()) {
            return null;
        } else {
            System.out.println("执行了获取最小值方法");
            T min = firstNode.data;
            Node minNode = firstNode;
            Node currentNode = firstNode;
            while (currentNode != null) {
                if (comparator.compare(currentNode.data, min) < 0) {
                    min = currentNode.data;
                    minNode = currentNode;
                }
                currentNode = currentNode.next;
            }
            return minNode;
        }
    }

    public T getMin(Comparator<T> comparator) {
        Node minNode = getMinRefference(comparator);
        if (minNode == null) {
            return null;
        } else {
            return minNode.data;
        }
    }

    private Node getMaxRefference(Comparator<T> comparator) {
        if (isEmpty()) {
            return null;
        } else {
            T max = firstNode.data;
            Node maxNode = firstNode;
            Node currentNode = firstNode;
            while (currentNode != null) {
                if (comparator.compare(currentNode.data, max) > 0) {
                    max = currentNode.data;
                    maxNode = currentNode;
                }
                currentNode = currentNode.next;
            }
            return maxNode;
        }
    }

    public T getMax(Comparator<T> comparator) {
        Node maxNode = getMaxRefference(comparator);
        if (maxNode == null) {
            return null;
        } else {
            return maxNode.data;
        }
    }

    public T removeMin(Comparator<T> comparator) {
        T result = null;
        Node minNode = getMinRefference(comparator);
        if (minNode != null) {
            result = minNode.data;
            minNode.data = firstNode.data;
            remove();
        }
        return result;
    }

    public T removeMax(Comparator<T> comparator) {
        T result = null;
        Node maxNode = getMaxRefference(comparator);
        if (maxNode != null) {
            result = maxNode.data;
            maxNode.data = firstNode.data;
            remove();
        }
        return result;
    }

    public BagInterface<T> getAllLessThan(T entry, Comparator<T> comparator) {
        BagInterface<T> result = new LinkedBag<>();
        Node currentNode = firstNode;
        while (currentNode != null) {
            if (comparator.compare(currentNode.data, entry) < 0) {
                result.add(currentNode.data);
            }
            currentNode = currentNode.next;
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedBag<T> linkedBag = (LinkedBag<T>) o;

        if (this.numberOfEntries == linkedBag.numberOfEntries) {
            Node currentNode = firstNode;
            while (currentNode != null) {
                if (this.getFrequencyOf(currentNode.data) != linkedBag.getFrequencyOf(currentNode.data)) {
                    return false;
                }
                currentNode = currentNode.next;
            }
            return true;
        } else {
            return false;
        }
    }

    public LinkedBag<T> union(LinkedBag<T> anotherBag) {
        LinkedBag<T> unionResult = new LinkedBag<>();
        Node currentNode = firstNode;
        while (currentNode != null) {
            unionResult.add(currentNode.data);
            currentNode = currentNode.next;
        }

        currentNode = anotherBag.firstNode;
        while (currentNode != null) {
            unionResult.add(currentNode.data);
            currentNode = currentNode.next;
        }
        return unionResult;
    }


    public LinkedBag<T> intersection(LinkedBag<T> anotherBag) {
        LinkedBag<T> intersectionResult = new LinkedBag<>();
        Node currentNode = firstNode;
        while (currentNode!=null) {
            int number = anotherBag.getFrequencyOf(currentNode.data);
            //只有当前元素也在对面存在, 而且当前元素不存在于结果中的时候才进行操作
            if (number > 0 && !intersectionResult.contains(currentNode.data)) {
                int thisNumber = this.getFrequencyOf(currentNode.data);
                //取两个数量的较小部分 = count
                int count = Math.min(number, thisNumber);
                //然后将当前元素添加count次
                while (count > 0) {
                    intersectionResult.add(currentNode.data);
                    count--;
                }
            }
            currentNode = currentNode.next;
        }

        return intersectionResult;
    }

    public LinkedBag<T> difference(LinkedBag<T> anotherBag) {
        LinkedBag<T> differenceResult = new LinkedBag<>();
        Node currentNode = firstNode;
        while (currentNode != null) {

            int number = anotherBag.getFrequencyOf(currentNode.data);
//            System.out.println("当前的元素是: " + bag[i]);
            if (!differenceResult.contains(currentNode.data)) {
                int thisNumber = this.getFrequencyOf(currentNode.data);
                //根据两个数量来得到结果 count, 如果自己的数量比其大, 就添加差额, 如果相等或者小于0, 就不添加.
                int count = Math.max(thisNumber - number, 0);
                //然后将当前元素添加count次
//                System.out.println("当前的值是: " + bag[i] + " 在自己包中出现几次: " + thisNumber + " 在另外一个包内出现几次: " + number + "要添加几次: " + count);
                while (count > 0) {
                    differenceResult.add(currentNode.data);
                    count--;
                }
            }
            currentNode = currentNode.next;
        }

        return differenceResult;
    }


}
