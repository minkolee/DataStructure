package datastructure.chapter10;

public class LinkedDeque<T> implements DequeInterface<T> {

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

    public LinkedDeque() {
        this.firstNode = null;
        this.lastNode = null;
    }


    //队列头部就是链表头, 尾就是链表尾
    @Override
    public void addToFront(T entry) {

        if (entry == null) {
            throw new RuntimeException("不允许向队列中放入null值");
        }

        //创建一个新节点
        Node newNode = new Node(entry);
        newNode.next = firstNode;
        //如果为空同时将头尾节点指向newNode
        if (isEmpty()) {
            //为空要同时更新首尾节点
            firstNode = newNode;
            lastNode = newNode;
            //不为空则不用更新lastNode
        } else {
            firstNode = newNode;
        }

    }

    //从队列头部插入新数据
    @Override
    public void addToBack(T entry) {

        //如果队列为空, 和从尾部插入一样, 所以调用其他方法即可
        if (isEmpty()) {
            addToFront(entry);

        } else {
            //创建新节点, 让lastNode.next指向该节点, 然后移动一次lastNode即可
            lastNode.next = new Node(entry);
            lastNode = lastNode.next;
        }

    }

    //从队列尾部删除, 这个方法需要在删除之后重置lastNode
    @Override
    public T removeBack() {

        T result = null;

        if (!isEmpty()) {
            result = lastNode.data;
            //如果只有一个节点, 删完就是空的了, 就设置first和last都是null
            if (firstNode == lastNode) {
                lastNode = null;
                firstNode = null;
            } else {
            //否则需要先找到lastNode的上一个, 然和重设
                Node currentNode = firstNode;
                while (currentNode.next != lastNode) {
                    currentNode = currentNode.next;
                }
                lastNode = currentNode;
                lastNode.next = null;
            }
        }
        return result;
    }

    @Override
    public T getBack() {
        if (isEmpty()) {
            return null;
        } else {
            return firstNode.data;

        }

    }

    //从lastNode那里出队就是removeBack()方法
    @Override
    public T removeFront() {
        //获取首节点
        T result = null;

        //队列不为空的话获取第一个节点的数据, 然后将firstNode指向其next, 标准的从链表头部删除元素的操作
        if (!isEmpty()) {
            Node target = firstNode;
            firstNode = firstNode.next;
            target.next = null;
            result = target.data;
            //删除结束之后, 还要再看一眼链表是否为空, 如果为空说明刚删除了唯一一个元素, 此时lastNode还指向那个元素, 需要将其设置为null.
            if (firstNode == null) {
                lastNode = null;
            }
        }
        return result;
    }

    @Override
    public T getFront() {
        if (isEmpty()) {
            return null;
        } else {
            return firstNode.data;
        }
    }

    @Override
    public boolean isEmpty() {
        return firstNode == null && lastNode == null;
    }

    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
    }

    private String showAll() {
        Node currentNode = firstNode;

        StringBuilder builder = new StringBuilder();

        while (currentNode != null) {
            builder.append(currentNode.toString());
            currentNode = currentNode.next;
            if (currentNode != null) {
                builder.append(" -> ");
            } else {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public void show() {
        System.out.println("first=" + firstNode);
        System.out.println("last=" + lastNode);
    }

    @Override
    public String toString() {
        return showAll();
    }
}
