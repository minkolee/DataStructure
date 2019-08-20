package alog4e.chapter01.section02.exercise;

import alog4e.libs.StdOut;

import java.util.Random;

public class DoubleNode<T> {

    T item;
    DoubleNode previous;
    DoubleNode next;

    //在表头插入节点, 比较简单
    public static DoubleNode insertIntoHead(DoubleNode newNode, DoubleNode linkedList) {
        if (linkedList == null) {
            return newNode;
        }
        if (newNode == null) {
            return null;
        }
        DoubleNode temp = linkedList;
        linkedList = newNode;
        newNode.next = temp;
        temp.previous = newNode;
        return linkedList;
    }

    public static DoubleNode append(DoubleNode newNode, DoubleNode linkedList) {
        if (linkedList == null) {
            return newNode;
        }
        DoubleNode start = linkedList;
        while (start.next != null) {
            start = start.next;
        }

        start.next = newNode;
        newNode.next = null;
        newNode.previous = start;
        return linkedList;
    }

    public static DoubleNode shift(DoubleNode linkedlist) {
        if (linkedlist == null) {
            return null;
        }

        if (linkedlist.next == null) {
            return null;
        }

        linkedlist.next.previous = null;
        return linkedlist.next;
    }

    public static DoubleNode deleteTail(DoubleNode linkedlist) {
        if (linkedlist == null) {
            return null;
        }
        if (linkedlist.next == null) {
            return null;
        }
        DoubleNode temp = linkedlist;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.previous.next = null;
        temp.previous = null;
        return linkedlist;
    }

    //在指定节点之前插入新节点, 需要获取指定节点和上一个节点, 然后更改一系列操作
    public static DoubleNode insertBefore(int index, DoubleNode newNode, DoubleNode linkedList) {
        if (linkedList == null) {
            return null;
        }
        if (newNode == null) {
            return linkedList;
        }

        if (index == 0) {
            return insertIntoHead(newNode, linkedList);
        }
        DoubleNode current = linkedList, last;

        for (int i = 0; i < index; i++) {
            current = current.next;
            if (current == null) {
                throw new RuntimeException("Index " + index + " out of bound!");
            }
        }

        last = current.previous;
        last.next = newNode;
        newNode.next = current;
        current.previous = newNode;
        newNode.previous = last;
        return linkedList;
    }

    public static DoubleNode insertAfter(int index, DoubleNode<Integer> newNode, DoubleNode<Integer> linkedList) {
        if (linkedList == null) {
            return null;
        }
        if (newNode == null) {
            return linkedList;
        }

        DoubleNode current = linkedList, next;

        for (int i = 0; i < index; i++) {
            current = current.next;
            if (current == null) {
                throw new RuntimeException("Index " + index + " out of bound!");
            }
        }
        next = current.next;
        current.next = newNode;
        newNode.next = next;
        newNode.previous = current;
        if (next != null) {
            next.previous = newNode;
        }
        return linkedList;
    }

    public static DoubleNode deleteNode(int index, DoubleNode linkedList) {
        if (index < 0) {
            throw new RuntimeException("index must be 0+");
        }
        if (linkedList == null) {
            return null;
        }

        if (index == 0) {
            return shift(linkedList);
        }

        DoubleNode current = linkedList;
        for (int i = 0; i < index; i++) {
            current = current.next;
            if (current == null) {
                throw new RuntimeException("Index " + index + " out of bound!");
            }
        }

        DoubleNode previous = current.previous;
        DoubleNode next = current.next;
        previous.next = next;
        if (next != null) {
            next.previous = previous;
        }
        return linkedList;
    }


    @Override
    public String toString() {
        return "DoubleNode{" +
                "item=" + item +
                '}';
    }

    public static DoubleNode<Integer> getIntLinkedList(int N) {

        if (N <= 0) {
            throw new RuntimeException("长度必须大于0");
        }

        DoubleNode<Integer>[] nodes = new DoubleNode[N];

        Random random = new Random();

        for (int i = 0; i < N; i++) {
            DoubleNode<Integer> newNode = new DoubleNode<>();
            newNode.item = i + random.nextInt(100);
            nodes[i] = newNode;
        }

        //注意前向指针
        if (N > 1) {
            for (int j = 0; j < N - 1; j++) {
                nodes[j].next = nodes[j + 1];
                if (j >= 1) {
                    nodes[j].previous = nodes[j - 1];
                }
            }
            nodes[N - 1].previous = nodes[N - 2];
        }

        return nodes[0];
    }

    public static void main(String[] args) {
        DoubleNode<Integer> linkedList = getIntLinkedList(5);
        Node.walk(linkedList);

//        StdOut.println("-----------");
        DoubleNode<Integer> newNode = new DoubleNode<>();
        newNode.item = 100;
        DoubleNode<Integer> newNode2 = new DoubleNode<>();
        newNode2.item = 200;
//        DoubleNode<Integer> newNode3 = new DoubleNode<>();
//        newNode3.item = 300;
//        DoubleNode<Integer> newNode4 = new DoubleNode<>();
//        newNode4.item = 400;
//        linkedList = insertIntoHead(newNode, linkedList);
//        linkedList = insertIntoHead(newNode2, linkedList);
//        linkedList = append(newNode3, linkedList);
//        linkedList = append(newNode4, linkedList);
//        Node.walk(linkedList);
//        StdOut.println("---------------------------");

        StdOut.println("---------------------------");
        linkedList = insertAfter(4, newNode2, linkedList);
        Node.walk(linkedList);

        StdOut.println("---------------------------");
        linkedList = deleteNode(6, linkedList);
        Node.walk(linkedList);

    }
}
