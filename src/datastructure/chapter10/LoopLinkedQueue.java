package datastructure.chapter10;

public class LoopLinkedQueue<T> implements QueueInterface<T> {

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

    //表示被数据占用部分的开始节点
    private Node queueNode;
    //表示空白部分的开始节点
    private Node vacancyNode;

    public LoopLinkedQueue() {
        //创建一个新的包含null值的节点
        Node startNode = new Node(null);
        //将其指向自己
        startNode.next = startNode;
        //然后将两个引用都指向这个节点.
        queueNode = startNode;
        vacancyNode = startNode;

    }

    @Override
    public void enqueue(T entry) {
        if (entry == null) {
            throw new RuntimeException("不允许将null入队");
        }

        //只要插入新数据, 必定要将当前空白节点装入新数据
        vacancyNode.data = entry;

        //如果队列满, 在当前位置下一个插入一个新节点
        if (isFull()) {
            Node newNode = new Node(null);
            newNode.next = vacancyNode.next;
            vacancyNode.next = newNode;
        }
        //如果不满, 就移动一次vacancyNode
        vacancyNode = vacancyNode.next;

    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已经为空");
        }

        T result = queueNode.data;
        queueNode.data = null;
        queueNode = queueNode.next;
        return result;
    }

    @Override
    public T getFront() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        } else {
            return queueNode.data;
        }

    }

    @Override
    public boolean isEmpty() {
        return vacancyNode == queueNode;
    }

    @Override
    public void clear() {
        if (!isEmpty()) {
            while (queueNode != vacancyNode) {
                queueNode.data = null;
                queueNode = queueNode.next;
            }
        }
    }

    private boolean isFull() {
        return vacancyNode.next == queueNode;
    }

    public void show() {
        Node currentNode = queueNode;
        while (currentNode != vacancyNode) {
            System.out.print(currentNode.data);
            if (currentNode.next != vacancyNode) {
                System.out.print(" -> ");
            }
            currentNode = currentNode.next;
        }
        System.out.println();

    }

}
