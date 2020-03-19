package datastructure.chapter10;

import datastructure.chapter08.LinkedList;

public class LinkedQueue<T> implements QueueInterface<T> {

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

    public LinkedQueue() {
        this.firstNode = null;
        this.lastNode = null;
    }


    @Override
    public void enqueue(T entry) {

        if (entry == null) {
            throw new RuntimeException("不允许向队列中放入null值");
        }

        //创建一个新节点
        Node newNode = new Node(entry);

        //由于是尾部插入, 不管如何lastNode都要等于newNode, 所以可以放在判断语句之外
        if (isEmpty()) {
            //为空要同时更新首尾节点
            firstNode = newNode;
        } else {
            //不为空则只需要更新尾节点即可
            lastNode.next = newNode;
        }
        lastNode = newNode;

    }

    @Override
    public T dequeue() {
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
