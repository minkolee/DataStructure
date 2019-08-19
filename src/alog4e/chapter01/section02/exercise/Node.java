package alog4e.chapter01.section02.exercise;

import alog4e.libs.In;
import alog4e.libs.StdOut;

import java.util.Random;

public class Node<T> {
    T item;
    Node<T> next;

    @Override
    public String toString() {
        return "Node{" +
                "item=" + item +
                '}';
    }

    public static Node<String> getLinkedList() {
        Node<String> n1 = new Node<>();
        n1.item = "cony1";
        Node<String> n2 = new Node<>();
        n2.item = "cony2";
        n1.next = n2;
        Node<String> n3 = new Node<>();
        n3.item = "cony3";
        n2.next = n3;
        Node<String> n4 = new Node<>();
        n4.item = "cony4";
        n3.next = n4;
        Node<String> n5 = new Node<>();
        n5.item = "cony5";
        n4.next = n5;
        return n1;
    }

    public static void walk(Node node) {
        while (node != null) {
            StdOut.println(node);
            node = node.next;
        }
    }

    public static Node<Integer> getIntLinkedList() {
        Node<Integer>[] nodes = new Node[10];

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            Node<Integer> newNode = new Node<>();
            newNode.item = i + random.nextInt(100);

            nodes[i] = newNode;
        }




        for (int j = 0; j < 9; j++) {
            nodes[j].next = nodes[j + 1];
        }
        return nodes[0];
    }
}
