package datastructure.chapter08;


public class LinkedList<T extends Comparable<? super T>> {

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
    private int numberOfEntries;
    private boolean isSorted = false;
    private boolean isSortedDesc = false;


    public LinkedList() {
        this.firstNode = null;
        numberOfEntries = 0;
    }

    /**
     * 对链表就地升序排序方法
     * 内部调用核心排序方法的升序版本
     */
    public void sort() {
        if (!isSorted) {
            Node unsortedNode = firstNode;
            firstNode = null;

            while (unsortedNode != null) {
                Node currentNode = unsortedNode;
                unsortedNode = unsortedNode.next;
                insertIntoNodes(currentNode, false);
            }
            isSorted = true;
            isSortedDesc = false;
        }

    }

    /**
     * 对链表就地降序排序方法
     * 内部调用核心排序方法的降序版本
     */
    public void sortDesc() {

        if (!isSortedDesc) {
            Node unsortedNode = firstNode;
            firstNode = null;

            while (unsortedNode != null) {
                Node currentNode = unsortedNode;
                unsortedNode = unsortedNode.next;
                insertIntoNodes(currentNode, true);

            }
            isSortedDesc = true;
            isSorted = false;
        }

    }

    /**
     * 将一个节点插入到一个已经排序的链表的方法. 核心原理是将被插入的节点的数据与被插入的链表中每个节点进行比较, 根据是否降序找到大于/小于被插入的节点的数据的那一项, 然后将节点插入至那一项之前
     * 如果遍历都没能够找到插入位置, 说明需要将被插入的节点插入到节点末尾
     *
     * @param insertedNode 要插入的节点
     * @param reverse      升序还是降序, true表示降序, false表示升序
     */

    private void insertIntoNodes(Node insertedNode, boolean reverse) {

        //如果已排序链表中没有元素, 直接将其插入第一个节点,
        if (firstNode == null) {
            insertedNode.next = null;
            firstNode = insertedNode;
        } else {
            //获取要插入的节点的数据
            T data = insertedNode.data;

            //开始遍历已排序链表, 先弄出两个引用, 一个指向遍历到的节点, 一个指向上一个节点, 对于第一个节点, 前一个节点为空
            Node previousNode = null;
            Node currentNode = firstNode;

            boolean inserted = false;
            //对于链表中每一个节点
            while (currentNode != null && !inserted) {
                //比较节点与要插入节点的大小, 这里先以升序排列为例, 则要找到比要插入节点大的节点, 将要插入的节点插入在那个节点之前

                //如果某个节点的数据大于要插入的节点, 说明要插入到这个节点之前.
                //根据是否降序来选择比较的结果
                if (!reverse) {
                    if (currentNode.data.compareTo(data) > 0) {
                        //如果上一个节点为空, 说明是首节点, 就跟往链表中插入一个新元素一样
                        if (previousNode == null) {
                            insertedNode.next = firstNode;
                            firstNode = insertedNode;

                            //如果不为空, 将其插入到上一个和当前节点之间
                        } else {
                            insertedNode.next = currentNode;
                            previousNode.next = insertedNode;
                        }
                        //只要找到了大于的部分, 就说明已经插入完了, break循环
                        inserted = true;
                        //如果要某个节点的数据没有大于要插入的节点, 继续寻找下一个节点
                    } else {
                        previousNode = currentNode;
                        currentNode = currentNode.next;
                    }
                } else {
                    if (currentNode.data.compareTo(data) < 0) {
                        //如果上一个节点为空, 说明是首节点, 就跟往链表中插入一个新元素一样
                        if (previousNode == null) {
                            insertedNode.next = firstNode;
                            firstNode = insertedNode;

                            //如果不为空, 将其插入到上一个和当前节点之间
                        } else {
                            insertedNode.next = currentNode;
                            previousNode.next = insertedNode;
                        }
                        //只要找到了大于的部分, 就说明已经插入完了, break循环
                        inserted = true;
                        //如果要某个节点的数据没有大于要插入的节点, 继续寻找下一个节点
                    } else {
                        previousNode = currentNode;
                        currentNode = currentNode.next;
                    }
                }
            }

            //如果一直到末尾现在都没有插入成功, 说明当前元素是最小/最大的, 已经到达链表末尾, 将元素插入到末尾
            if (!inserted) {
                previousNode.next = insertedNode;
                insertedNode.next = null;
            }

        }
    }

    public boolean add(T entry) {
        Node newNode = new Node(entry, firstNode);
        firstNode = newNode;
        numberOfEntries++;
        isSorted = false;
        isSortedDesc = false;
        return true;
    }

    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    public T remove() {
        T result = null;

        if (!isEmpty()) {
            result = firstNode.data;
            firstNode = firstNode.next;
            numberOfEntries--;
        }
        return result;
    }

    public void showAllEntries() {
        Node currentNode = firstNode;
        while (currentNode != null) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }
        System.out.println();

    }

    public void clear() {
        this.firstNode = null;
        this.numberOfEntries = 0;
    }

    public Object[] toArray() {

        Object[] result = new Object[numberOfEntries];
        Node currentNode = firstNode;
        int index = 0;
        int number = numberOfEntries;
        while (number != 0) {
            result[index] = currentNode.data;
            currentNode = currentNode.next;
            index++;
            number--;
        }

        return result;
    }

}
